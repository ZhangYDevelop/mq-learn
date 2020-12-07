package com.zy.mq.consumer.listener.rocketmq;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "orderly_group",topic = "orderly_topic", consumeMode = ConsumeMode.ORDERLY)
public class OrderlyMessageListener  implements RocketMQListener<String> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(String message) {
        logger.info("Receive message :{},CurrentTimeMillis: {}", message, System.currentTimeMillis());
    }
}
