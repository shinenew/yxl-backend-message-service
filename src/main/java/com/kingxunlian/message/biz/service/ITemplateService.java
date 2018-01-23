package com.kingxunlian.message.biz.service;

import com.kingxunlian.message.dto.request.MessageTemplateAddRequest;
import com.kingxunlian.message.dto.response.MessageTemplateResponse;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/22 下午3:02
 */
public interface ITemplateService {

    MessageTemplateResponse addMessageTemplate(MessageTemplateAddRequest addRequest);



}
