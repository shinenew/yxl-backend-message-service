package com.kingxunlian.message.biz.dto;

import com.kingxunlian.message.dto.enums.MessageTypeEnum;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/24 下午2:35
 */
public class SendMessageBatchDto implements Serializable{

    /**
     * 发送的消息ID
     */
    private Long messageId;

    /**
     * 接收的用户列表
     */
    private List<UUID> receiveUserList;

    /**
     * 消息类型
     */
    private MessageTypeEnum messageType;


    public Long getMessageId() {
        return messageId;
    }

    public SendMessageBatchDto setMessageId(Long messageId) {
        this.messageId = messageId;
        return this;
    }

    public List<UUID> getReceiveUserList() {
        return receiveUserList;
    }

    public SendMessageBatchDto setReceiveUserList(List<UUID> receiveUserList) {
        this.receiveUserList = receiveUserList;
        return this;
    }

    public MessageTypeEnum getMessageType() {
        return messageType;
    }

    public SendMessageBatchDto setMessageType(MessageTypeEnum messageType) {
        this.messageType = messageType;
        return this;
    }
}
