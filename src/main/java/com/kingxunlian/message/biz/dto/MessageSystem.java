package com.kingxunlian.message.biz.dto;

import java.io.Serializable;
import java.util.Date;

public class MessageSystem implements Serializable {
    /**
     * 系统消息主键
     */
    private Long messageId;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 消息标题
     */
    private String messageTitle;

    /**
     * 消息跳转地址
     */
    private String messageUrl;

    /**
     * 消息扩展第字段
     */
    private String messageExtra;

    /**
     * 消息生成时间
     */
    private Date postDate;

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

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle == null ? null : messageTitle.trim();
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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}