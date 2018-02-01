package com.kingxunlian.message.biz.controller;

import com.kingxunlian.common.XLBaseResponse;
import com.kingxunlian.message.biz.service.IEmailService;
import com.kingxunlian.message.dto.request.EmailSendRequest;
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
 * @Date: create in 2018/2/1 上午11:37
 */
@RestController
@RequestMapping("/message/email")
public class EmailController {


    @Autowired
    private IEmailService emailService;


    @RequestMapping(value = "/sendSimpleEmail",method = RequestMethod.POST)
    @ApiOperation("消息管理-发送简单邮件")
    public XLBaseResponse<Boolean> sendSimpleEmail(@RequestBody @Valid EmailSendRequest emailSendRequest){
       Boolean result =  emailService.sendSimpleMail(emailSendRequest);
       return XLBaseResponse.newInstance(result);
    }
}
