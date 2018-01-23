package com.kingxunlian.message.biz.dao;

import com.alibaba.fastjson.JSON;
import com.kingxunlian.message.biz.dto.MessageText;
import com.kingxunlian.message.biz.dto.MessageTextExample;
import com.kingxunlian.message.biz.mapper.MessageTextMapper;
import com.kingxunlian.utils.mybatis.MybatisExampleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/17 下午5:48
 */
@Component
public class MessageTextDao {

    private static final Logger logger = LoggerFactory.getLogger(MessageTextDao.class);

    @Autowired
    private MessageTextMapper messageTextMapper;


    /**
     * 插入一条新数据
     * @param messageText
     * @return
     */
    public MessageText insert(MessageText messageText){
        messageTextMapper.insert(messageText);
        return messageText;
    }

    /**
     * 根据条件查询单条
     * @param messageText
     * @return
     */
    public MessageText findOneByFilter(MessageText messageText){
        MessageTextExample example = new MessageTextExample();
        MybatisExampleUtils.genCriteriaByFilter(example.createCriteria(),messageText);
        List<MessageText> messageTextList = messageTextMapper.selectByExample(example);
        if (messageTextList.size() == 0){
            logger.warn("MessageText query result is null,condition is:{}", JSON.toJSONString(messageText));
            return null;
        }
        return messageTextList.get(0);
    }

    /**
     * 根据条件查询多条数据
     * @param messageText
     * @return
     */
    public List<MessageText> findListByFilter(MessageText messageText){
        MessageTextExample example = new MessageTextExample();
        MybatisExampleUtils.genCriteriaByFilter(example.createCriteria(),messageText);
        List<MessageText> messageTextList = messageTextMapper.selectByExample(example);
        if (messageTextList.size() == 0){
            logger.warn("MessageText query result is null,condition is:{}", JSON.toJSONString(messageText));
        }
        return messageTextList;
    }


    /**
     * 根据条件查询多条数据
     * @param messageList
     * @return
     */
    public List<MessageText> findListByMessageIdList(List<Long> messageList){
        MessageTextExample example = new MessageTextExample();
        example.createCriteria().andMessageIdIn(messageList);
        List<MessageText> messageTextList = messageTextMapper.selectByExample(example);
        if (messageTextList.size() == 0){
            logger.warn("MessageText query result is null,condition is:{}", JSON.toJSONString(messageList));
        }
        return messageTextList;
    }

    /**
     * 根据查询条件更新数据
     * @param updateRecord
     * @param filter
     * @return
     */
    public MessageText updateBySelective(MessageText updateRecord,MessageText filter){
        MessageTextExample example = new MessageTextExample();
        MybatisExampleUtils.genCriteriaByFilter(example.createCriteria(),filter);
        messageTextMapper.updateByExampleSelective(updateRecord,example);
        return updateRecord;
    }

}
