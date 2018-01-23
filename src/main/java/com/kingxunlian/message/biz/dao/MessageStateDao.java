package com.kingxunlian.message.biz.dao;

import com.kingxunlian.message.biz.dto.MessageState;
import com.kingxunlian.message.biz.dto.MessageStateExample;
import com.kingxunlian.message.biz.mapper.MessageStateMapper;
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
public class MessageStateDao {

    private static final Logger logger = LoggerFactory.getLogger(MessageStateDao.class);

    @Autowired
    private MessageStateMapper messageStateMapper;


    /**
     * 插入一条消息数据
     * @param messageState
     * @return
     */
    public MessageState insert(MessageState messageState){
        messageStateMapper.insert(messageState);
        return messageState;
    }


    /**
     * 通过条件查询单条
     * @param filter
     * @return
     */
    public MessageState findOneByFilter(MessageState filter){
        MessageStateExample stateExample = new MessageStateExample();
        MybatisExampleUtils.genCriteriaByFilter(stateExample.createCriteria(),filter);
        List<MessageState> messageStateList = messageStateMapper.selectByExample(stateExample);
        if (messageStateList.size() == 0){
            logger.warn("Message query result is null,condition is:{}",filter);
            return null;
        }
        return messageStateList.get(0);
    }


    /**
     * 通过条件查询多条
     * @param filter
     * @return
     */
    public List<MessageState> findListByFilter(MessageState filter){
        MessageStateExample stateExample = new MessageStateExample();
        MybatisExampleUtils.genCriteriaByFilter(stateExample.createCriteria(),filter);
        List<MessageState> messageStateList = messageStateMapper.selectByExample(stateExample);
        if (messageStateList.size() == 0){
            logger.warn("Message query result is null,condition is:{}",filter);
        }
        return messageStateList;
    }


    /**
     * 根据查询条件更新消息的数据
     * @param updateState
     * @param filter
     * @return
     */
    public MessageState updateBySelective(MessageState updateState,MessageState filter){
        MessageStateExample stateExample = new MessageStateExample();
        MybatisExampleUtils.genCriteriaByFilter(stateExample.createCriteria(),filter);
        messageStateMapper.updateByExampleSelective(updateState,stateExample);
        return updateState;
    }


}
