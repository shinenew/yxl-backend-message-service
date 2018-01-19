package com.kingxunlian.message.mq;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2017/12/22 下午4:47
 */
public class MQProducerDto<T> {

    private String topic;

    private String key;

    private T body;


    public String getTopic() {
        return topic;
    }

    public MQProducerDto setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getKey() {
        return key;
    }

    public MQProducerDto setKey(String key) {
        this.key = key;
        return this;
    }

    public T getBody() {
        return body;
    }

    public MQProducerDto setBody(T body) {
        this.body = body;
        return this;
    }
}
