package com.kingxunlian.message.biz.consumer;

import com.kingxunlian.message.biz.dto.SendMessageBatchDto;
import com.kingxunlian.message.biz.service.IMessageService;
import com.kingxunlian.message.config.MessageConfigParam;
import com.kingxunlian.mq.consumer.ConsumeModel;
import com.kingxunlian.mq.consumer.ConsumeStatus;
import com.kingxunlian.mq.consumer.MQMessage;
import com.kingxunlian.mq.consumer.impl.AbstractMQConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Descriptions:
 * @Author: chenliang@tsfinance.com
 * @Date: create in 2018/1/24 下午2:50
 */

@Component
public class SendBatchMessageConsumer extends AbstractMQConsumer<SendMessageBatchDto> {

    private static final Logger logger = LoggerFactory.getLogger(SendBatchMessageConsumer.class);

    @Autowired
    MessageConfigParam messageConfigParam;

    @Autowired
    private IMessageService messageService;

    @Override
    public String topic() {
        return messageConfigParam.getMessageBatchSendTopic();
    }

    @Override
    public String consumerGroup() {
        return messageConfigParam.getMessageBatchSendConsumer();
    }

    @Override
    public boolean needOrdered() {
        return false;
    }

    @Override
    public ConsumeModel consumeModel() {
        return ConsumeModel.CLUSTERING;
    }

    @Override
    public ConsumeStatus doConsume(List<MQMessage<SendMessageBatchDto>> list) {
        SendMessageBatchDto req = list.get(0).getContent();
        try {
            messageService.consumerAndSendBatchMessage(req);
        }catch (Exception e){
            logger.error("SendBatchMessageConsumer send batch message failed :{}",e);
            e.printStackTrace();
            return ConsumeStatus.SUCCESS;
        }
        logger.debug("SendBatchMessageConsumer send batch message :{} success!", req.getMessageId());
        return ConsumeStatus.SUCCESS;
    }


}
