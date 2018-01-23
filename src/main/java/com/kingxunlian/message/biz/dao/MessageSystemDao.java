package com.kingxunlian.message.biz.dao;

import com.alibaba.fastjson.JSON;
import com.kingxunlian.message.biz.dto.MessageSystem;
import com.kingxunlian.message.biz.dto.MessageSystemExample;
import com.kingxunlian.message.biz.mapper.MessageSystemMapper;
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
public class MessageSystemDao {

    private static final Logger logger = LoggerFactory.getLogger(MessageSystemDao.class);


    @Autowired
    private MessageSystemMapper messageSystemMapper;


    /**
     * 插入一条消息
     * @param messageSystem
     * @return
     */
    public MessageSystem insert(MessageSystem messageSystem){
        messageSystemMapper.insert(messageSystem);
        return messageSystem;
    }

    /**
     * 根据主键查询消息
     * @param messageId
     * @return
     */
    public MessageSystem findByMessageId(Long messageId){
        return messageSystemMapper.selectByPrimaryKey(messageId);

    }
    /**
     * 根据筛选条件查询一条数据
     * @param messageSystem
     * @return
     */
    public MessageSystem findOneByFilter(MessageSystem messageSystem){
        MessageSystemExample example = new MessageSystemExample();
        MybatisExampleUtils.genCriteriaByFilter(example.createCriteria(),messageSystem);
        List<MessageSystem> messageSystemList = messageSystemMapper.selectByExample(example);
        if (messageSystemList.size() == 0){
            logger.warn("SystemMessage query result is null,condition is:{}", JSON.toJSONString(messageSystem));
            return null;
        }
        return messageSystemList.get(0);
    }

    /**
     * 根据筛选条件查询多条数据
     * @param messageSystem
     * @return
     */
    public List<MessageSystem> findListByFilter(MessageSystem messageSystem){
        MessageSystemExample example = new MessageSystemExample();
        MybatisExampleUtils.genCriteriaByFilter(example.createCriteria(),messageSystem);
        List<MessageSystem> messageSystemList = messageSystemMapper.selectByExample(example);
        if (messageSystemList.size() == 0){
            logger.warn("SystemMessage query result is null,condition is:{}", JSON.toJSONString(messageSystem));
        }
        return messageSystemList;
    }

    /**
     * 根据筛选条件更新数据
     * @param updateRecord
     * @param filter
     * @return
     */
    public MessageSystem updateBySelective(MessageSystem updateRecord,MessageSystem filter){
        MessageSystemExample example = new MessageSystemExample();
        MybatisExampleUtils.genCriteriaByFilter(example.createCriteria(),filter);
        messageSystemMapper.updateByExampleSelective(updateRecord,example);
        return updateRecord;
    }



}
