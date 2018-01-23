package com.kingxunlian.message.biz.dao;

import com.alibaba.fastjson.JSON;
import com.kingxunlian.message.biz.dto.MessageTemplate;
import com.kingxunlian.message.biz.dto.MessageTemplateExample;
import com.kingxunlian.message.biz.mapper.MessageTemplateMapper;
import com.kingxunlian.utils.mybatis.MybatisExampleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/22 上午10:29
 */
@Component
public class MessageTemplateDao {

    private static final Logger logger = LoggerFactory.getLogger(MessageTemplateDao.class);

    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    /**
     * 插入一条模版数据
     * @param messageTemplate
     * @return
     */
    public MessageTemplate insert(MessageTemplate messageTemplate){
        messageTemplateMapper.insert(messageTemplate);
        return messageTemplate;
    }


    /**
     * 条件查询单条记录
     * @param filter
     * @return
     */
    public MessageTemplate findOneByFilter(MessageTemplate filter){
        MessageTemplateExample example = new MessageTemplateExample();
        MybatisExampleUtils.genCriteriaByFilter(example.createCriteria(),filter);
        List<MessageTemplate> messageTemplateList = messageTemplateMapper.selectByExample(example);
        if (messageTemplateList.size() == 0){
            logger.warn("MessageTemplate is null,query condition is :{}", JSON.toJSONString(filter));
            return null;
        }
        return messageTemplateList.get(0);
    }

    /**
     * 根据查询条件更新数据
     * @param template
     * @param filter
     * @return
     */
    public MessageTemplate updateRecordBySeletive(MessageTemplate template,MessageTemplate filter){
        MessageTemplateExample example = new MessageTemplateExample();
        MybatisExampleUtils.genCriteriaByFilter(example.createCriteria(),filter);
        messageTemplateMapper.updateByExampleSelective(template,example);
        return filter;
    }


    /**
     * 根据主键更新模版
     * @param messageTemplate
     * @return
     */
    public MessageTemplate updateByPrimaryKey(MessageTemplate messageTemplate){
        messageTemplateMapper.updateByPrimaryKey(messageTemplate);
        return messageTemplate;
    }

}
