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
}
