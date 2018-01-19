package com.kingxunlian.message.mq;

import com.kingxunlian.common.XLBaseResponse;
import com.kingxunlian.mq.producer.MQGatewayClient;
import com.kingxunlian.mq.producer.dto.SendMessageRequest;
import com.kingxunlian.mq.producer.dto.SyncSendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2017/12/22 下午4:34
 */

@Component
public class MQProducer {

    private static final Logger logger = LoggerFactory.getLogger(MQProducer.class);

    @Autowired
    private MQGatewayClient mqGatewayClient;


    /**
     * 发送通知单内容到消息队列
     * @return
     */
    public String sendToQueue(MQProducerDto dto){
        SendMessageRequest request = new SendMessageRequest();
        request.setTopic(dto.getTopic());
        request.setKey(dto.getKey());
        request.setContent(dto.getBody());
        request.setSendTimeout(3000);//发送超时时间
        request.setRetryWhenSendFailed(0);//发送失败不重试
        XLBaseResponse<SyncSendResult> response = mqGatewayClient.sendAsyncMessage(request);
        if(response.isOk()){
            return "ok";
        }
        logger.info("MQProducer send:{} to topic:{} success!",dto.getKey(),dto.getTopic());
        return response.getBody().getMessageId();
    }

}
