package com.kingxunlian.message.biz.service;

import com.kingxunlian.message.dto.request.MessageSendRequest;
import com.kingxunlian.message.dto.response.MessageSendResponse;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/17 下午5:46
 */
public interface IMessageService {

    MessageSendResponse sendMessage(MessageSendRequest request);
}
