package com.zy.mq.rocketmq.service.impl;

import com.zy.mq.rocketmq.service.MqMessageService;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MqMessageServiceImpl implements MqMessageService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public ResponseEntity sendSyncMessage() {
        String[] array = new String[]{"消息1", "消息2", "消息3","消息4", "消息5", "消息6"};
        for (String s : array) {
            Message<String> message = MessageBuilder.withPayload(s).build();
            rocketMQTemplate.syncSendOrderly("orderly_topic", message, UUID.randomUUID().toString());
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity sendAsyncMessage() {
        String[] array = new String[]{"消息1", "消息2", "消息3","消息4", "消息5", "消息6"};
        for (String s : array) {
            Message<String> message = MessageBuilder.withPayload(s).build();
            rocketMQTemplate.asyncSendOrderly("orderly_topic", message, UUID.randomUUID().toString(), new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
//                    logger.info("Message send seccuss ");
                }

                @Override
                public void onException(Throwable throwable) {
//                    logger.info("Message send error, message:  {}", throwable.getMessage());
                }
            });
        }
        return ResponseEntity.ok().build();
    }
}
