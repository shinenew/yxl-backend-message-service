package com.kingxunlian.message.biz.service;

import com.kingxunlian.common.PageList;
import com.kingxunlian.message.biz.dto.SendMessageBatchDto;
import com.kingxunlian.message.dto.request.MessageListFilter;
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

    PageList<MessageSendResponse> getUserMessage(UUID userId, Integer pageNum, Integer pageSize);

    Integer getUserUnreadMessageCount(UUID userId);

    void consumerAndSendBatchMessage(SendMessageBatchDto sendMessageBatchDto);

    MessageSendResponse readMessage(UUID userId,Long messageId);

    Boolean deleteMessage(UUID userId,Long messageId);

    Boolean deleteAllMessage(UUID userId);

    PageList<MessageSendResponse> getMessageList(MessageListFilter filter);
}
