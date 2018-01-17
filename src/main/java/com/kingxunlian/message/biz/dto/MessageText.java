package com.kingxunlian.message.biz.dto;

import com.kingxunlian.message.dto.enums.MessageTypeEnum;
import java.io.Serializable;
import java.util.Date;

public class MessageText implements Serializable {
    /**
     * 消息唯一ID
     */
    private Long messageId;

    /**
     * 消息内容，不能为空
     */
    private String messageContent;

    /**
     * 消息发送时间
     */
    private Date postDate;

    /**
     * 消息标题
     */
    private String messageTitle;

    /**
     * 消息创建人
     */
    private String creatorId;

    /**
     * 消息类型
     */
    private MessageTypeEnum messageType;

    /**
     * 消息所属系统
     */
    private String messageSystem;

    /**
     * 消息跳转的地址
     */
    private String messageUrl;

    /**
     * 消息扩展字段
     */
    private String messageExtra;

    private static final long serialVersionUID = 1L;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle == null ? null : messageTitle.trim();
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public MessageTypeEnum getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypeEnum messageType) {
        this.messageType = messageType;
    }

    public String getMessageSystem() {
        return messageSystem;
    }

    public void setMessageSystem(String messageSystem) {
        this.messageSystem = messageSystem == null ? null : messageSystem.trim();
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl == null ? null : messageUrl.trim();
    }

    public String getMessageExtra() {
        return messageExtra;
    }

    public void setMessageExtra(String messageExtra) {
        this.messageExtra = messageExtra == null ? null : messageExtra.trim();
    }
}