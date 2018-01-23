package com.kingxunlian.message.biz.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.kingxunlian.exception.XLException;
import com.kingxunlian.message.biz.dao.MessageStateDao;
import com.kingxunlian.message.biz.dao.MessageSystemDao;
import com.kingxunlian.message.biz.dao.MessageTemplateDao;
import com.kingxunlian.message.biz.dao.MessageTextDao;
import com.kingxunlian.message.biz.dto.MessageState;
import com.kingxunlian.message.biz.dto.MessageSystem;
import com.kingxunlian.message.biz.dto.MessageTemplate;
import com.kingxunlian.message.biz.dto.MessageText;
import com.kingxunlian.message.dto.enums.MessageStateEnum;
import com.kingxunlian.message.dto.request.MessageSendRequest;
import com.kingxunlian.message.dto.response.MessageSendResponse;
import com.kingxunlian.message.exception.MessageErrorCodeEnum;
import com.kingxunlian.message.idgen.IdGenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/17 下午5:46
 */
@Service
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
            sendSystemMessage(request,messageTemplate);
        }else if (request.getReceiveUserList().size() == 1 && request.getSendUser() != null){
            //1.send_user不为空且receive_user长度为1，为私信
            sendSingleMessage(request,template);
        }else {
            //其他情况默认为批量消息
            sendBatchMessage(request);
        }
        return new MessageSendResponse();
    }


    /**
     * 发送单条消息
     * @param request
     * @return
     */
    public MessageText sendSingleMessage(MessageSendRequest request,MessageTemplate template){
        Long messageId = idGenHelper.getLongId();
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
        messageState.setReceiveUser(request.getReceiveUserList().get(0));
        messageState.setCreateTime(new Date());
        messageState.setIsDelete(0);
        messageState.setMessageState(MessageStateEnum.UNREAD);
        messageState.setMessageType(request.getMessageType());
        messageStateDao.insert(messageState);
        //TODO 判断当前用户是否有缓存，有则更新缓存里面的消息条数
        return messageText;
    }

    /**
     * 发送多条消息
     * @param request
     * @return
     */
    public List<MessageText> sendBatchMessage(MessageSendRequest request){
        MessageText messageText = new MessageText();
        return Lists.newArrayList(messageText);

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
        }catch (Exception e){
            String msg = MessageFormat.format("Render message template failed,message is:{0}",e.getMessage());
            logger.error(msg);
            throw new XLException(msg,MessageErrorCodeEnum.TEMPLATE_RENDER_FAILED);
        }
        return content;
    }


    /**
     * 初始化用户的未读消息
     * @param userId
     * @return
     */
    public Integer initUserMessage(UUID userId){
        MessageState query = new MessageState();
        query.setReceiveUser(userId);
//        query.setMessageState(MessageStateEnum.UNREAD);
//        query.setIsDelete(0);
        List<MessageState> unreadMsgList = messageStateDao.findListByFilter(query);
        List<Long> messageId = unreadMsgList.stream()
                .map(messageState -> {
                    return messageState.getMessageId();
                        }
                ).collect(Collectors.toList());
        List<MessageText> unReadMessageList = messageTextDao.findListByMessageIdList(messageId);
        //查询系统消息
        MessageSystem messageSystem = new MessageSystem();
        List<MessageSystem> messageSystemList = messageSystemDao.findListByFilter(messageSystem);
        //选择不在state表中的记录插入text表中


        return unReadMessageList.size();
    }

}
