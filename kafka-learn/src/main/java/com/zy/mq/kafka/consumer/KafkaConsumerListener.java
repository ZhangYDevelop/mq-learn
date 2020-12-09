package com.zy.mq.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
    @KafkaListener(topics = "kafkatopic2")
    public void testMessage2(String content) {
        System.out.println("====KafkaReceiver test=====："+content);
    }
}
