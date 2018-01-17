package com.kingxunlian.message.biz.service;

import com.kingxunlian.message.biz.dao.MessageStateDao;
import com.kingxunlian.message.biz.dao.MessageSystemDao;
import com.kingxunlian.message.biz.dao.MessageTextDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/17 下午5:46
 */
@Service
public class MessageService implements IMessageService{

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageStateDao messageStateDao;

    @Autowired
    private MessageTextDao messageTextDao;

    @Autowired
    private MessageSystemDao messageSystemDao;



}
