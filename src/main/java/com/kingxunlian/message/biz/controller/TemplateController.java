package com.kingxunlian.message.biz.controller;

import com.kingxunlian.common.PageList;
import com.kingxunlian.common.XLBaseResponse;
import com.kingxunlian.message.biz.dto.MessageTemplate;
import com.kingxunlian.message.biz.service.TemplateService;
import com.kingxunlian.message.dto.request.MessageTemplateAddRequest;
import com.kingxunlian.message.dto.request.MessageTemplateListFilter;
import com.kingxunlian.message.dto.response.MessageTemplateResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/22 下午4:15
 */
@RestController
@RequestMapping("/message/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    /**
     * 保存模版
     * @param addRequest
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ApiOperation("模板管理-保存模板")
    public XLBaseResponse<MessageTemplateResponse> saveTemplate(@RequestBody @Valid MessageTemplateAddRequest addRequest){
        MessageTemplateResponse response = templateService.addMessageTemplate(addRequest);
        return XLBaseResponse.newInstance(response);
    }


}
