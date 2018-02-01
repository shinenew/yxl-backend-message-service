package com.kingxunlian.message.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.logback.LogbackLoggingSystem;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2017/12/13 下午5:37
 */
@Component("messageConfigParam")
@RefreshScope
public class MessageConfigParam {

    private static final Logger logger = LoggerFactory.getLogger(MessageConfigParam.class);


    @Value("${redis.message.count.expire.time}")
    private Long messageCountExpireTime;

    @Value("${rocketmq.topic.batch.send}")
    private String messageBatchSendTopic;

    @Value("${rocketmq.consumer.group.batch.send}")
    private String messageBatchSendConsumer;

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private Integer mailPort;

    @Value("${spring.mail.username}")
    private String mailUserName;

    @Value("${spring.mail.password}")
    private String mailUserPassword;

    //==================== 配置自动刷新 start =========================

    @Autowired
    private org.springframework.cloud.context.scope.refresh.RefreshScope refreshScope;

    @Autowired
    private LogbackLoggingSystem logbackLoggingSystem;

    @ApolloConfigChangeListener
    private void someChangeHandler(ConfigChangeEvent changeEvent) {
        logger.info("someChangeHandler, refreshScope.refreshAll()");
        if (changeEvent.isChanged("logging.level")) {
            String level = changeEvent.getChange("logging.level").getNewValue();
            logbackLoggingSystem.setLogLevel(null, LogLevel.valueOf(level));
        }
        refreshScope.refresh("messageConfigParam");
    }
    //==================== 配置自动刷新 end ===========================


    public Long getMessageCountExpireTime() {
        return messageCountExpireTime;
    }

    public MessageConfigParam setMessageCountExpireTime(Long messageCountExpireTime) {
        this.messageCountExpireTime = messageCountExpireTime;
        return this;
    }

    public String getMessageBatchSendTopic() {
        return messageBatchSendTopic;
    }

    public MessageConfigParam setMessageBatchSendTopic(String messageBatchSendTopic) {
        this.messageBatchSendTopic = messageBatchSendTopic;
        return this;
    }

    public String getMessageBatchSendConsumer() {
        return messageBatchSendConsumer;
    }

    public MessageConfigParam setMessageBatchSendConsumer(String messageBatchSendConsumer) {
        this.messageBatchSendConsumer = messageBatchSendConsumer;
        return this;
    }

    public String getMailHost() {
        return mailHost;
    }

    public MessageConfigParam setMailHost(String mailHost) {
        this.mailHost = mailHost;
        return this;
    }

    public Integer getMailPort() {
        return mailPort;
    }

    public MessageConfigParam setMailPort(Integer mailPort) {
        this.mailPort = mailPort;
        return this;
    }

    public String getMailUserName() {
        return mailUserName;
    }

    public MessageConfigParam setMailUserName(String mailUserName) {
        this.mailUserName = mailUserName;
        return this;
    }

    public String getMailUserPassword() {
        return mailUserPassword;
    }

    public MessageConfigParam setMailUserPassword(String mailUserPassword) {
        this.mailUserPassword = mailUserPassword;
        return this;
    }
}
