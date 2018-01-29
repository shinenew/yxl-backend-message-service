package com.kingxunlian.message.biz.controller;

import com.alibaba.fastjson.JSON;
import com.kingxunlian.common.PageList;
import com.kingxunlian.common.XLBaseResponse;
import com.kingxunlian.exception.XLException;
import com.kingxunlian.message.biz.dto.MessageTemplate;
import com.kingxunlian.message.biz.service.IMessageService;
import com.kingxunlian.message.biz.service.ITemplateService;
import com.kingxunlian.message.dto.request.BackendLoginRequest;
import com.kingxunlian.message.dto.request.MessageListFilter;
import com.kingxunlian.message.dto.request.MessageTemplateAddRequest;
import com.kingxunlian.message.dto.request.MessageTemplateListFilter;
import com.kingxunlian.message.dto.response.MessageSendResponse;
import com.kingxunlian.message.dto.response.MessageTemplateResponse;
import com.kingxunlian.message.exception.MessageErrorCodeEnum;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/26 上午9:56
 */

@RestController
@RequestMapping("/api")
public class MessageBackendController {

    private static final Logger logger = LoggerFactory.getLogger(MessageBackendController.class);


    @Autowired
    private IMessageService messageService;

    @Autowired
    private ITemplateService templateService;


    /**
     * 模拟后台登录接口
     * @param loginRequest
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public XLBaseResponse<String> login(@RequestBody @Valid BackendLoginRequest loginRequest){
        String token = "23h29u7sdf92h1se209ue3121";
        if ("chenliang".equals(loginRequest.getLoginName()) && "test123".equals(loginRequest.getLoginPassword())){
            return XLBaseResponse.newInstance(token);
        }else {
            throw  new XLException("账号或密码错误", MessageErrorCodeEnum.SERVER_INNER_ERROR);
        }
    }

    /**
     * 后台查询消息列表
     * @param filter
     * @return
     */
    @RequestMapping(value = "/messageList",method = RequestMethod.POST)
    public XLBaseResponse<PageList<MessageSendResponse>> getMessageList(@RequestBody @Valid MessageListFilter filter){
        PageList<MessageSendResponse> userMessageList = messageService.getMessageList(filter);
        return XLBaseResponse.newInstance(userMessageList);
    }


    /**
     * 查询模版列表
     * @param filter
     * @return
     */
    @RequestMapping(value = "/templateList",method = RequestMethod.POST)
    @ApiOperation("模版管理-查询模版")
    public XLBaseResponse<PageList<MessageTemplate>> listTemplate(@RequestBody @Valid MessageTemplateListFilter filter){
        PageList<MessageTemplate> dataList = templateService.listTemplateByFilter(filter);
        return XLBaseResponse.newInstance(dataList);
    }

    /**
     * 查询模版列表
     * @param filter
     * @return
     */
    @RequestMapping(value = "/templateState",method = RequestMethod.POST)
    @ApiOperation("模版管理-查询模版")
    public XLBaseResponse<Boolean> updateTemplateState(@RequestBody @Valid MessageTemplate filter){
        Boolean result = templateService.updateTemplateState(filter);
        logger.info("Update Message Request is:{}", JSON.toJSONString(filter));
        return XLBaseResponse.newInstance(result);
    }


    /**
     * 查询模版列表
     * @param filter
     * @return
     */
    @RequestMapping(value = "/templateDelete",method = RequestMethod.POST)
    @ApiOperation("模版管理-删除模版")
    public XLBaseResponse<String> deleteTemplate(@RequestBody @Valid MessageTemplate filter){
        logger.debug("Update Message Request is:{}", JSON.toJSONString(filter));
        return XLBaseResponse.newInstance("OK");
    }


    /**
     * 保存模版
     * @param addRequest
     * @return
     */
    @RequestMapping(value = "/templateSave",method = RequestMethod.POST)
    @ApiOperation("模板管理-保存模板")
    public XLBaseResponse<MessageTemplateResponse> saveTemplate(@RequestBody @Valid MessageTemplateAddRequest addRequest){
        MessageTemplateResponse response = templateService.addMessageTemplate(addRequest);
        return XLBaseResponse.newInstance(response);
    }


}
