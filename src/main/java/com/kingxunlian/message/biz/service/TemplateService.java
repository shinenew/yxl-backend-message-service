package com.kingxunlian.message.biz.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kingxunlian.common.PageList;
import com.kingxunlian.message.biz.dao.MessageTemplateDao;
import com.kingxunlian.message.biz.dto.MessageState;
import com.kingxunlian.message.biz.dto.MessageTemplate;
import com.kingxunlian.message.dto.request.MessageTemplateAddRequest;
import com.kingxunlian.message.dto.request.MessageTemplateListFilter;
import com.kingxunlian.message.dto.response.MessageTemplateResponse;
import com.kingxunlian.message.idgen.IdGenHelper;
import com.kingxunlian.utils.CommonUtils;
import com.kingxunlian.utils.PageListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/22 下午3:02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TemplateService implements ITemplateService{

    private static final Logger logger = LoggerFactory.getLogger(TemplateService.class);

    @Autowired
    private MessageTemplateDao  messageTemplateDao;

    @Autowired
    private IdGenHelper idGenHelper;


    /**
     * 新增一条消息模版
     * @param addRequest
     * @return
     */
    public MessageTemplateResponse addMessageTemplate(MessageTemplateAddRequest addRequest){
        MessageTemplateResponse messageTemplateResponse = new MessageTemplateResponse();
        //判断模块是否存在
        MessageTemplate templateQuery = new MessageTemplate();
        templateQuery.setTemplateCode(addRequest.getTemplateCode());
        MessageTemplate messageTemplate = messageTemplateDao.findOneByFilter(templateQuery);
        if (messageTemplate == null){
            Long templateId  = idGenHelper.getLongId();
            MessageTemplate createTemplate = new MessageTemplate();
            createTemplate.setTemplateCode(addRequest.getTemplateCode());
            createTemplate.setTemplateName(addRequest.getTemplateName());
            createTemplate.setTemplateTitle(addRequest.getTemplateName());
            createTemplate.setTemplateApp(addRequest.getTemplateApp());
            createTemplate.setTemplateContext(addRequest.getTemplateContext());
            createTemplate.setTemplateUrl(addRequest.getTemplateUrl());
            createTemplate.setTemplateId(templateId);
            createTemplate.setTemplateEnable((byte)1);
            createTemplate.setCreateTime(new Date());
            messageTemplateDao.insert(createTemplate);
            messageTemplateResponse.setTemplateId(templateId);
            messageTemplateResponse.setTemplateCode(createTemplate.getTemplateCode());
        }else {
            //更新模块的内容
            messageTemplate.setTemplateContext(addRequest.getTemplateContext());
            messageTemplate.setTemplateUrl(addRequest.getTemplateUrl());
            messageTemplate.setTemplateName(addRequest.getTemplateName());
            messageTemplate.setTemplateEnable((byte)1);
            messageTemplate.setUpdateTime(new Date());
            messageTemplate.setTemplateApp(addRequest.getTemplateApp());
            messageTemplateDao.updateByPrimaryKey(messageTemplate);
            messageTemplateResponse.setTemplateCode(messageTemplate.getTemplateCode());
            messageTemplateResponse.setTemplateId(messageTemplate.getTemplateId());
        }
        return messageTemplateResponse;
    }

    /**
     * 查询模板列表
     * @param templateListFilter
     * @return
     */
    public PageList<MessageTemplate> listTemplateByFilter(MessageTemplateListFilter templateListFilter){
        final Page<MessageTemplate> page = PageHelper.startPage(
                templateListFilter.getPageNum(),
                templateListFilter.getPageSize());
        messageTemplateDao.findListByFilter(templateListFilter);
        return PageListUtil.createPageList(page);
    }


    /**
     * 修改模版状态
     * @param messageTemplate
     * @return
     */
    public Boolean updateTemplateState(MessageTemplate messageTemplate){
        MessageTemplate update = new MessageTemplate();
        update.setTemplateEnable(messageTemplate.getTemplateEnable());
        update.setUpdateTime(new Date());
        MessageTemplate filter = new MessageTemplate();
        filter.setTemplateCode(messageTemplate.getTemplateCode());
        messageTemplateDao.updateRecordBySeletive(update,filter);
        return true;
    }


    /**
     * 获取模版内容
     * @param templateId
     * @return
     */
    public MessageTemplateResponse getMessageTemplate(Long templateId){
        MessageTemplate filter = new MessageTemplate();
        filter.setTemplateId(templateId);
        MessageTemplate messageTemplate = messageTemplateDao.findOneByFilter(filter);
        MessageTemplateResponse messageTemplateResponse  = new MessageTemplateResponse();
        CommonUtils.beanCopy(messageTemplate,messageTemplateResponse);
        return messageTemplateResponse;
    }


}
