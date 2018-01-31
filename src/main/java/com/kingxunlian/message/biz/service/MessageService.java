package com.kingxunlian.message.biz.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.kingxunlian.common.PageList;
import com.kingxunlian.exception.XLException;
import com.kingxunlian.message.biz.dao.MessageStateDao;
import com.kingxunlian.message.biz.dao.MessageSystemDao;
import com.kingxunlian.message.biz.dao.MessageTemplateDao;
import com.kingxunlian.message.biz.dao.MessageTextDao;
import com.kingxunlian.message.biz.dto.MessageState;
import com.kingxunlian.message.biz.dto.MessageSystem;
import com.kingxunlian.message.biz.dto.MessageTemplate;
import com.kingxunlian.message.biz.dto.MessageText;
import com.kingxunlian.message.biz.dto.SendMessageBatchDto;
import com.kingxunlian.message.config.MessageConfigParam;
import com.kingxunlian.message.dto.enums.MessageStateEnum;
import com.kingxunlian.message.dto.enums.MessageTypeEnum;
import com.kingxunlian.message.dto.request.MessageListFilter;
import com.kingxunlian.message.dto.request.MessageSendRequest;
import com.kingxunlian.message.dto.response.MessageSendResponse;
import com.kingxunlian.message.exception.MessageErrorCodeEnum;
import com.kingxunlian.message.idgen.IdGenHelper;
import com.kingxunlian.message.mq.MQProducer;
import com.kingxunlian.message.mq.MQProducerDto;
import com.kingxunlian.message.redis.RedisTemplateKeyUtil;
import com.kingxunlian.utils.CommonUtils;
import com.kingxunlian.utils.PageListUtil;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/17 下午5:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageService implements IMessageService{

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageStateDao messageStateDao;

    @Autowired
    private MessageTextDao messageTextDao;

    @Autowired
    private MessageSystemDao messageSystemDao;

    @Autowired
    private MessageTemplateDao messageTemplateDao;

    @Autowired
    private IdGenHelper idGenHelper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MessageConfigParam messageConfigParam;

    @Autowired
    private MQProducer mqProducer;


    /**
     * 发送消息
     * @param request
     * @return
     */
    public MessageSendResponse sendMessage(MessageSendRequest request){
        String templateCode = request.getTemplateCode();
        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setTemplateCode(templateCode);
        MessageTemplate template = messageTemplateDao.findOneByFilter(messageTemplate);
        if (template == null){
            String msg = MessageFormat.format("Message template is not found,{0}",templateCode);
            logger.error(msg);
            throw new XLException(msg, MessageErrorCodeEnum.TEMPLATE_NOT_FOUND);
        }else if ((byte)0 == template.getTemplateEnable()){
            String msg = MessageFormat.format("Message template is disabled,:{0}",templateCode);
            logger.error(msg);
            throw new XLException(msg,MessageErrorCodeEnum.TEMPLATE_IS_DISABLED);
        }
        //消息类型判断，主要是校验不同消息类型的参数传入情况
        if (request.getReceiveUserList() == null){
            //在message_system插入一条记录
            sendSystemMessage(request,template);
        }else if (request.getReceiveUserList().size() == 1 && request.getSendUser() != null){
            //1.send_user不为空且receive_user长度为1，为私信
            sendSingleMessage(request,template);
        }else {
            //其他情况默认为批量消息
            sendBatchMessage(request,template);
        }
        return new MessageSendResponse();
    }


    /**
     * 将一条消息标记为已读
     * 并返回消息的详细内容
     * @param messageId
     * @return
     */
    public MessageSendResponse readMessage(UUID userId,Long messageId){
        MessageState stateQuery = new MessageState();
        stateQuery.setReceiveUser(userId);
        stateQuery.setMessageId(messageId);
        MessageState messageState = messageStateDao.findOneByFilter(stateQuery);
        if (messageState == null){
            String msg = MessageFormat.format("Message :{0} not found!",messageId.toString());
            logger.error(msg);
            throw new XLException(msg,MessageErrorCodeEnum.MESSAGE_NOT_FOUND);
        }
        if (!MessageStateEnum.UNREAD.equals(messageState.getMessageState())){
            logger.error("Message is not unread,state error!");
            return null;
        }
        MessageSendResponse messageSendResponse = new MessageSendResponse();
        //装入消息数据
        messageSendResponse.setMessageId(messageId);
        messageSendResponse.setCreateTime(messageState.getCreateTime());
        messageSendResponse.setUpdateTime(messageState.getUpdateTime());
        messageSendResponse.setIsDelete(messageState.getIsDelete());
        messageSendResponse.setMessageState(messageState.getMessageState());
        getMessageTextByMessageId(messageSendResponse,messageId);
        messageState.setMessageState(MessageStateEnum.READ);
        messageState.setUpdateTime(new Date());
        messageStateDao.updaupdateByPrimaryKeyteBy(messageState);
        return messageSendResponse;
    }

    /**
     * 删除消息
     * @param userId
     * @param messageId
     * @return
     */
    public Boolean deleteMessage(UUID userId,Long messageId){
        MessageState stateQuery = new MessageState();
        stateQuery.setReceiveUser(userId);
        stateQuery.setMessageId(messageId);
        MessageState messageState = messageStateDao.findOneByFilter(stateQuery);
        if (messageState == null){
            String msg = MessageFormat.format("Message :{0} not found!",messageId.toString());
            logger.error(msg);
            throw new XLException(msg,MessageErrorCodeEnum.MESSAGE_NOT_FOUND);
        }
        //判断是否已经删除
        if (0 != messageState.getIsDelete()){
            logger.error("Message is deleted,state error!");
            return false;
        }
        messageState.setIsDelete(1);
        messageState.setUpdateTime(new Date());
        messageStateDao.updaupdateByPrimaryKeyteBy(messageState);
        return true;
    }

    /**
     * 删除当前用户下的所有消息
     * @param userId
     * @return
     */
    public Boolean deleteAllMessage(UUID userId){
        MessageState stateQuery = new MessageState();
        stateQuery.setReceiveUser(userId);
        stateQuery.setIsDelete(0);
        MessageState state = new MessageState();
        state.setIsDelete(1);
        messageStateDao.updateBySelective(state,stateQuery);
        return true;
    }



    /**
     * 发送单条消息
     * @param request
     * @return
     */
    public MessageText sendSingleMessage(MessageSendRequest request,MessageTemplate template){
        Long messageId = idGenHelper.getLongId();
        UUID receiveUserId = request.getReceiveUserList().get(0);
        //插入一条MessageText记录
        MessageText messageText = new MessageText();
        messageText.setMessageId(messageId);
        messageText.setMessageUrl(template.getTemplateUrl());
        messageText.setMessageType(request.getMessageType());
        messageText.setPostDate(new Date());
        messageText.setMessageSystem(request.getAppCode());
        messageText.setCreatorId(request.getSendUser().toString());
        //替换模版的参数
        Map<String,String> messageParameters = (Map<String,String>)JSON.parseObject(request.getMessageParameter(),Map.class);
        String messageContext = renderTemplate(template.getTemplateContext(),messageParameters);
        messageText.setMessageContent(messageContext);
        messageText.setMessageExtra(request.getMessageExtra());
        messageTextDao.insert(messageText);
        //像用户表中插入一条记录
        MessageState messageState = new MessageState();
        messageState.setMessageId(messageId);
        messageState.setSendUser(request.getSendUser());
        messageState.setReceiveUser(receiveUserId);
        messageState.setCreateTime(new Date());
        messageState.setIsDelete(0);
        messageState.setMessageState(MessageStateEnum.UNREAD);
        messageState.setMessageType(request.getMessageType());
        messageStateDao.insert(messageState);
        // 判断当前用户是否有缓存，有则更新缓存里面的消息条数
        List<MessageState> userUnreadMessages = getUserUnreadMessage(receiveUserId);
        String userMessageCountKey = RedisTemplateKeyUtil.getUserUnreadMessageCountKey(receiveUserId);
        String userUnreadMessageCount = String.valueOf(userUnreadMessages.size());
        if (redisTemplate.hasKey(userMessageCountKey)){
            redisTemplate.delete(userMessageCountKey);
            redisTemplate.opsForValue().set(userMessageCountKey,
                    userUnreadMessageCount,
                    messageConfigParam.getMessageCountExpireTime(),
                    TimeUnit.MINUTES);
        }
        return messageText;
    }

    /**
     * 发送多条消息
     * @param request
     * @return
     */
    public List<MessageText> sendBatchMessage(MessageSendRequest request,MessageTemplate template){
        List<UUID> receiveUserList = request.getReceiveUserList();
        Long messageId = idGenHelper.getLongId();
        MessageText messageText = new MessageText();
        //插入一条MessageText记录
        messageText.setMessageId(messageId);
        messageText.setMessageUrl(template.getTemplateUrl());
        messageText.setMessageType(request.getMessageType());
        messageText.setPostDate(new Date());
        messageText.setMessageSystem(request.getAppCode());
        messageText.setCreatorId(request.getSendUser().toString());
        //替换模版的参数
        Map<String,String> messageParameters = (Map<String,String>)JSON.parseObject(request.getMessageParameter(),Map.class);
        String messageContext = renderTemplate(template.getTemplateContext(),messageParameters);
        messageText.setMessageContent(messageContext);
        messageText.setMessageExtra(request.getMessageExtra());
        messageTextDao.insert(messageText);
        //发送消息到MQ
        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCommit() {
                        //发送mq
                        SendMessageBatchDto messageBatchDto = new SendMessageBatchDto();
                        messageBatchDto.setMessageId(messageId);
                        messageBatchDto.setMessageType(request.getMessageType());
                        messageBatchDto.setReceiveUserList(receiveUserList);
                        MQProducerDto<SendMessageBatchDto> producerDto = new MQProducerDto<>();
                        producerDto.setBody(messageBatchDto);
                        producerDto.setKey(messageId.toString());
                        producerDto.setTopic(messageConfigParam.getMessageBatchSendTopic());
                        mqProducer.sendToQueue(producerDto);
                    }
                }
        );
        return Lists.newArrayList(messageText);
    }


    /**
     * 消费消息并批量发送消息
     * @param sendMessageBatchDto
     */
    public void consumerAndSendBatchMessage(SendMessageBatchDto sendMessageBatchDto){
        List<UUID> receiveUserList = sendMessageBatchDto.getReceiveUserList();
        Long messageId = sendMessageBatchDto.getMessageId();
        MessageTypeEnum messageType = sendMessageBatchDto.getMessageType();
        //循环插入多条消息
        for (UUID userId:receiveUserList){
            try {
                MessageState messageState = new MessageState();
                messageState.setMessageId(messageId);
                messageState.setSendUser(userId);
                messageState.setReceiveUser(userId);
                messageState.setCreateTime(new Date());
                messageState.setIsDelete(0);
                messageState.setMessageState(MessageStateEnum.UNREAD);
                messageState.setMessageType(messageType);
                messageStateDao.insert(messageState);
                //判断当前用户是否有缓存，有则更新缓存里面的消息条数
                List<MessageState> userUnreadMessages = getUserUnreadMessage(userId);
                String userMessageCountKey = RedisTemplateKeyUtil.getUserUnreadMessageCountKey(userId);
                String userUnreadMessageCount = String.valueOf(userUnreadMessages.size());
                if (redisTemplate.hasKey(userMessageCountKey)){
                    redisTemplate.delete(userMessageCountKey);
                    redisTemplate.opsForValue().set(userMessageCountKey,
                            userUnreadMessageCount,
                            messageConfigParam.getMessageCountExpireTime(),
                            TimeUnit.MINUTES);
                }
            }catch (Exception e){
                logger.error("Send Message :{} to user:{} failed,exception message is :{}",messageId,userId,e.getMessage());
                e.printStackTrace();
            }
        }
    }


    /**
     * 发送系统消息
     * @param request
     * @return
     */
    public MessageSystem sendSystemMessage(MessageSendRequest request,MessageTemplate template){
        Long messageId = idGenHelper.getLongId();
        MessageSystem messageSystem = new MessageSystem();
        messageSystem.setMessageId(messageId);
        messageSystem.setMessageUrl(template.getTemplateUrl());
        //替换模版的参数
        Map<String,String> messageParameters = (Map<String,String>)JSON.parseObject(request.getMessageParameter(),Map.class);
        String messageContext = renderTemplate(template.getTemplateContext(),messageParameters);
        messageSystem.setMessageContent(messageContext);
        messageSystem.setMessageExtra(request.getMessageExtra());
        messageSystem.setMessageTitle("");
        messageSystem.setPostDate(new Date());
        messageSystemDao.insert(messageSystem);
        return messageSystem;
    }


    /**
     * 替换消息模板里面的参数如：${name}
     * @param content
     * @param map
     * @return
     */
    private   String renderTemplate(String content, Map<String, String> map){
        Set<Map.Entry<String, String>> sets = map.entrySet();
        try {
            for(Map.Entry<String, String> entry : sets) {
                String regex = "\\$\\{" + entry.getKey() + "\\}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(content);
                content = matcher.replaceAll(entry.getValue());
            }
            //如果替换后文本里还是有${},则抛出异常
            if (content.lastIndexOf("${") != -1){
                String msg = MessageFormat.format("Parameters and templates do not match,please check!Send Parameter is:{0}",JSON.toJSONString(map));
                logger.error(msg);
                throw new XLException(msg,MessageErrorCodeEnum.SERVER_INNER_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            String msg = MessageFormat.format("Render message template failed,message is:{0}",e.getMessage());
            logger.error(msg);
            throw new XLException(msg,MessageErrorCodeEnum.TEMPLATE_RENDER_FAILED);
        }
        return content;
    }


    /**
     * 初始化用户的未读消息
     *
     * @param userId
     * @return
     */
    public Integer initUserMessage(UUID userId) {
        MessageState query = new MessageState();
        query.setReceiveUser(userId);
        List<Long> userMessageIdList = messageStateDao.findListByFilter(query).stream()
                .map(messageState -> {
                            return messageState.getMessageId();
                        }
                ).collect(Collectors.toList());
//        List<MessageText> unReadMessageList = messageTextDao.findListByMessageIdList(userMessageIdList);
        //查询系统消息
        MessageSystem messageSystem = new MessageSystem();
        List<MessageSystem> messageSystemList = messageSystemDao.findListByFilter(messageSystem);
        //选择不在state表中的记录插入text表中
        List<Long> systemMessageIdList = new ArrayList<>();
        for (MessageSystem item : messageSystemList) {
            if (!userMessageIdList.contains(item.getMessageId())) {
                systemMessageIdList.add(item.getMessageId());
            }
        }
        for (Long messageId : systemMessageIdList){
            //插入未读数据到用户消息中
            MessageState messageState = new MessageState();
            messageState.setMessageId(messageId);
            messageState.setReceiveUser(userId);
            messageState.setCreateTime(new Date());
            messageState.setIsDelete(0);
            messageState.setMessageState(MessageStateEnum.UNREAD);
            messageState.setMessageType(MessageTypeEnum.SYSTEM);
            messageState.setSendUser(userId);
            messageStateDao.insert(messageState);
        }
        //查询用户的未读消息数，并且缓存
        List<MessageState> userNewUnreadMessages = getUserUnreadMessage(userId);
        String userUnreadMessageCount = String.valueOf(userNewUnreadMessages.size());
        //缓存用户的未读消息数
        String userMessageCountKey = RedisTemplateKeyUtil.getUserUnreadMessageCountKey(userId);
        if (redisTemplate.hasKey(userMessageCountKey)){
            redisTemplate.delete(userMessageCountKey);
        }
        redisTemplate.opsForValue().set(userMessageCountKey,
                userUnreadMessageCount,
                messageConfigParam.getMessageCountExpireTime(),
                TimeUnit.MINUTES);
        return userNewUnreadMessages.size();
    }

    /**
     * 获取用户的未读消息数
     * @param userId
     * @return
     */
    public Integer getUserUnreadMessageCount(UUID userId){
        String userMessageCountKey = RedisTemplateKeyUtil.getUserUnreadMessageCountKey(userId);
        if (redisTemplate.hasKey(userMessageCountKey)){
            String unreadCount = redisTemplate.opsForValue().get(userMessageCountKey);
            return Integer.parseInt(unreadCount);
        }
        List<MessageState> messageStateList = getUserUnreadMessage(userId);
        String unreadCount = String.valueOf(messageStateList.size());
        redisTemplate.opsForValue().set(userMessageCountKey,
                unreadCount,
                messageConfigParam.getMessageCountExpireTime(),
                TimeUnit.MINUTES);
        return messageStateList.size();
    }

    /**
     * 获取用户消息
     * @param userId
     * @return
     */
    public PageList<MessageSendResponse> getUserMessage(UUID userId, Integer pageNum, Integer pageSize){
        MessageState query = new MessageState();
        query.setIsDelete(0);
        query.setReceiveUser(userId);
        final Page<MessageState> page = PageHelper.startPage(pageNum,pageSize);
        List<MessageState> messageStateList = messageStateDao.findListByFilter(query);
        PageList<MessageState> messageStatePageList = PageListUtil.createPageList(page);
        //将分页数据装入聚合的Dto
        PageList<MessageSendResponse> responsePageList  = new PageList<>();
        responsePageList.setPageMeta(messageStatePageList.getPageMeta());
        List<MessageSendResponse> messageResponseList = getMessageTextByMessageId(messageStateList);
        responsePageList.setItems(messageResponseList);
        return responsePageList;
    }

    /**
     * 获取消息列表
     * @param filter
     * @return
     */
    public PageList<MessageSendResponse> getMessageList(MessageListFilter filter){
        final Page<MessageState> page = PageHelper.startPage(
                filter.getPageNum(),
                filter.getPageSize());
        List<MessageState> messageStateList = messageStateDao.findListByFilter(filter);
        PageList<MessageState> messageStatePageList = PageListUtil.createPageList(page);
        //将分页数据装入聚合的Dto
        PageList<MessageSendResponse> responsePageList  = new PageList<>();
        responsePageList.setPageMeta(messageStatePageList.getPageMeta());
        List<MessageSendResponse> messageResponseList = getMessageTextByMessageId(messageStateList);
        responsePageList.setItems(messageResponseList);
        return responsePageList;
    }


    /**
     * 根据用户的消息状态查询完整消息
     * @param messageStateList
     * @return
     */
    private List<MessageSendResponse> getMessageTextByMessageId(List<MessageState> messageStateList){
        List<MessageSendResponse> messageResponseList = messageStateList.stream()
                .map(messageState -> {
                    Long messageId = messageState.getMessageId();
                    MessageSendResponse messageSendResponse = new MessageSendResponse();
                    //装入消息数据
                    messageSendResponse.setMessageId(messageId);
                    messageSendResponse.setCreateTime(messageState.getCreateTime());
                    messageSendResponse.setUpdateTime(messageState.getUpdateTime());
                    messageSendResponse.setIsDelete(messageState.getIsDelete());
                    messageSendResponse.setMessageState(messageState.getMessageState());
                    getMessageTextByMessageId(messageSendResponse,messageId);
                    return messageSendResponse;
                }).collect(Collectors.toList());
        return messageResponseList;
    }

    /**
     * 查询用户的唯独消息数
     * @param userId
     * @return
     */
    private List<MessageState> getUserUnreadMessage(UUID userId){
        MessageState query = new MessageState();
        query.setMessageState(MessageStateEnum.UNREAD);
        query.setReceiveUser(userId);
        query.setIsDelete(0);
        List<MessageState> userUnreadMessages = messageStateDao.findListByFilter(query);
        return userUnreadMessages;
    }

    /**
     * 查询消息的详细内容
     * @param messageId
     * @return
     */
    private MessageSendResponse getMessageTextByMessageId(MessageSendResponse messageSendResponse ,Long messageId){
        MessageText messageText = messageTextDao.findByMessageId(messageId);
        //装入消息数据
        messageSendResponse.setMessageId(messageId);
        if (messageText != null){
            messageSendResponse.setMessageContent(messageText.getMessageContent());
            messageSendResponse.setMessageUrl(messageText.getMessageUrl());
            messageSendResponse.setMessageType(messageText.getMessageType());
            messageSendResponse.setMessageExtra(messageText.getMessageExtra());
            messageSendResponse.setMessageSystem(messageText.getMessageSystem());
        }else {
            MessageSystem messageSystem = messageSystemDao.findByMessageId(messageId);
            messageSendResponse.setMessageContent(messageSystem.getMessageContent());
            messageSendResponse.setMessageUrl(messageSystem.getMessageUrl());
            messageSendResponse.setMessageType(MessageTypeEnum.SYSTEM);
            messageSendResponse.setMessageExtra(messageSystem.getMessageExtra());
            messageSendResponse.setMessageSystem("系统消息");
        }
        return messageSendResponse;
    }


}
