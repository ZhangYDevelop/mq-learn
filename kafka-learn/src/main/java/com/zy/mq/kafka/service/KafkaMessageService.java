package com.zy.mq.kafka.service;

import org.springframework.http.ResponseEntity;

public interface KafkaMessageService {

    ResponseEntity sentMessage();
}
