package com.kingxunlian.message.config;


import com.kingxunlian.mq.NameSrvConfig;
import com.kingxunlian.mq.consumer.EnableConsumerClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConsumerClient
public class MQNameSrvConfig {

    //这里的占位符注入名称各应用可以自定义和配置
    @Value("${rocketmq.addr}")
    private String nameSrvAddress;

    //只要存在该bean的配置即可
    @Bean
    public NameSrvConfig nameSrvConfig(){
        return new NameSrvConfig(nameSrvAddress);
    }
}
