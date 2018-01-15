package com.kingxunlian.message.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by Yorke on 2017/6/27.
 */
@Configuration
@EnableAsync
public class ExecutorConfig {
    /**
     * 线程池维护线程的最小数量. .
     */
    private final int corePoolSize = 10;
    /**
     * 线程池维护线程的最大数量. .
     */
    private final int maxPoolSize = 100;
    /**
     * 队列最大长度.
     */
    private final int queueCapacity = 100;

    @Bean(name = "MessageExecutor")
    public Executor batchCreateNoticeBillExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("MessageExecutor-");
        executor.initialize();
        return executor;
    }
}
