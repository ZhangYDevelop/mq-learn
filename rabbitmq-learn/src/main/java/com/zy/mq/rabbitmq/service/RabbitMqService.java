package com.zy.mq.rabbitmq.service;

import org.springframework.http.ResponseEntity;

public interface RabbitMqService {

    ResponseEntity<String> sentMessage();
}
