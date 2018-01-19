package com.kingxunlian.message.biz.controller;

import com.kingxunlian.common.XLBaseResponse;
import com.kingxunlian.message.biz.service.IMessageService;
import com.kingxunlian.message.dto.request.MessageSendRequest;
import com.kingxunlian.message.dto.response.MessageSendResponse;
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
 * @Date: create in 2018/1/17 下午5:47
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IMessageService messageService;


    /**
     * 发送消息
     * @param messageSendRequest
     * @return
     */
    @RequestMapping(value = "/send",method = RequestMethod.POST)
    @ApiOperation("消息管理-发送消息")
    public XLBaseResponse<MessageSendResponse> sendMessage(@RequestBody @Valid MessageSendRequest messageSendRequest){
        MessageSendResponse messageSendResponse = messageService.sendMessage(messageSendRequest);
        return XLBaseResponse.newInstance(messageSendResponse);
    }
}
