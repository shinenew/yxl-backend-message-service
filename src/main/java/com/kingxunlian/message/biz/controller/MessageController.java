package com.kingxunlian.message.biz.controller;

import com.kingxunlian.common.XLBaseResponse;
import com.kingxunlian.context.XLRequestContextHolder;
import com.kingxunlian.context.XLRequestHeader;
import com.kingxunlian.message.biz.service.IMessageService;
import com.kingxunlian.message.dto.request.MessageSendRequest;
import com.kingxunlian.message.dto.response.MessageSendResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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

    /**
     * 初始化用户的消息
     * @return
     */
    @RequestMapping(value = "/initUserMessage",method = RequestMethod.GET)
    @ApiOperation("消息管理-初始化用户的未读消息")
    public XLBaseResponse<Integer> initUserMessage(){
       XLRequestHeader requestHeader =  XLRequestContextHolder.assertHeader();
        UUID userId = requestHeader.getUserId();
        Integer unreadMessageCount = messageService.initUserMessage(userId);
        return XLBaseResponse.newInstance(unreadMessageCount);
    }

    /**
     * 获取用户的未读消息数
     * @return
     */
    @RequestMapping(value = "/getUserUnreadCount",method = RequestMethod.GET)
    @ApiOperation("消息管理-获取用户的未读消息")
    public XLBaseResponse<Integer> getUserUnreadMessageCount(){
        XLRequestHeader requestHeader =  XLRequestContextHolder.assertHeader();
        UUID userId = requestHeader.getUserId();
        Integer unreadMessageCount = messageService.getUserUnreadMessageCount(userId);
        return XLBaseResponse.newInstance(unreadMessageCount);
    }

    /**
     * 获取用户消息
     * @return
     */
    @RequestMapping(value = "/getUserMessage",method = RequestMethod.GET)
    @ApiOperation("消息管理-获取用户消息")
    public XLBaseResponse<List<MessageSendResponse>> getUserMessage(){
        XLRequestHeader requestHeader =  XLRequestContextHolder.assertHeader();
        UUID userId = requestHeader.getUserId();
        List<MessageSendResponse> userMessageList = messageService.getUserMessage(userId);
        return XLBaseResponse.newInstance(userMessageList);
    }
}
