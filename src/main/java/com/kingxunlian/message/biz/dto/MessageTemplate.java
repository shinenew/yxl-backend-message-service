package com.kingxunlian.message.biz.dto;

import java.io.Serializable;
import java.util.Date;

public class MessageTemplate implements Serializable {
    /**
     * 消息模版ID
     */
    private Long templateId;

    /**
     * 模版编码
     */
    private String templateCode;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模版内容，不能超过200个字符
     */
    private String templateContext;

    /**
     * 模块跳转地址
     */
    private String templateUrl;

    /**
     * 模板所属系统
     */
    private String templateApp;

    /**
     * 模版是否启用
     */
    private Byte templateEnable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 模版标题
     */
    private String templateTitle;

    private static final long serialVersionUID = 1L;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode == null ? null : templateCode.trim();
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getTemplateContext() {
        return templateContext;
    }

    public void setTemplateContext(String templateContext) {
        this.templateContext = templateContext == null ? null : templateContext.trim();
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl == null ? null : templateUrl.trim();
    }

    public String getTemplateApp() {
        return templateApp;
    }

    public void setTemplateApp(String templateApp) {
        this.templateApp = templateApp == null ? null : templateApp.trim();
    }

    public Byte getTemplateEnable() {
        return templateEnable;
    }

    public void setTemplateEnable(Byte templateEnable) {
        this.templateEnable = templateEnable;
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

    public String getTemplateTitle() {
        return templateTitle;
    }

    public void setTemplateTitle(String templateTitle) {
        this.templateTitle = templateTitle == null ? null : templateTitle.trim();
    }
}