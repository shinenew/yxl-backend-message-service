package com.kingxunlian.message.biz.dto;

import com.kingxunlian.message.dto.enums.MessageStateEnum;
import com.kingxunlian.message.dto.enums.MessageTypeEnum;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class MessageState implements Serializable {
    private Integer id;

    private Long messageId;

    /**
     * 发送者ID
     */
    private UUID sendUser;

    /**
     * 接受用户ID
     */
    private UUID receiveUser;

    /**
     * 消息状态：0-未读，1-已读
     */
    private MessageStateEnum messageState;

    /**
     * 消息是否已经删除
     */
    private Integer isDelete;

    /**
     * 消息类型
     */
    private MessageTypeEnum messageType;

    /**
     * 消息创建时间
     */
    private Date createTime;

    /**
     * 消息更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public UUID getSendUser() {
        return sendUser;
    }

    public void setSendUser(UUID sendUser) {
        this.sendUser = sendUser;
    }

    public UUID getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(UUID receiveUser) {
        this.receiveUser = receiveUser;
    }

    public MessageStateEnum getMessageState() {
        return messageState;
    }

    public void setMessageState(MessageStateEnum messageState) {
        this.messageState = messageState;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public MessageTypeEnum getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypeEnum messageType) {
        this.messageType = messageType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}