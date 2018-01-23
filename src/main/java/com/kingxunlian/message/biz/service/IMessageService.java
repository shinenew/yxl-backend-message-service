package com.kingxunlian.message.biz.service;

import com.kingxunlian.message.dto.request.MessageSendRequest;
import com.kingxunlian.message.dto.response.MessageSendResponse;

import java.util.List;
import java.util.UUID;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/17 下午5:46
 */
public interface IMessageService {

    MessageSendResponse sendMessage(MessageSendRequest request);

    Integer initUserMessage(UUID userId);

    List<MessageSendResponse> getUserMessage(UUID userId);

    Integer getUserUnreadMessageCount(UUID userId);
}
