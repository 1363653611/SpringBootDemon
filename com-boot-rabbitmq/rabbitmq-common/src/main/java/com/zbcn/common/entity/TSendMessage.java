package com.zbcn.common.entity;

import java.util.Date;

/**
 * 发送消息
 */
public class TSendMessage {
    /**
     * 消息主键
     */
    private Long id;

    /**
     * 消息唯一id
     */
    private String messageId;

    /**
     * 消息发送者
     */
    private String sender;

    /**
     * 消息发送者id
     */
    private String senderId;

    /**
     * 接受者ID
     */
    private String receiveId;

    /**
     * 消息体
     */
    private String message;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 附件id
     */
    private String annexId;

    /**
     * 消息状态,1 有效，0 无效
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 备注
     */
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getAnnexId() {
        return annexId;
    }

    public void setAnnexId(String annexId) {
        this.annexId = annexId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}