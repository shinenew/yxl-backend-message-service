package com.kingxunlian.message.biz.service;

import com.kingxunlian.message.dto.request.EmailSendRequest;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/2/1 上午10:49
 */
public interface IEmailService {

    Boolean sendSimpleMail(EmailSendRequest emailSendRequest);
}
