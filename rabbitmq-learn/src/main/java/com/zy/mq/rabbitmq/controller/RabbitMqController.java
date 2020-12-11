package com.zy.mq.rabbitmq.controller;

import com.zy.mq.rabbitmq.service.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rabbit")
public class RabbitMqController {

    @Autowired
    private RabbitMqService rabbitMqService;
    @GetMapping("/sentMessage")
    public ResponseEntity createOrder() {
        return rabbitMqService.sentMessage();
    }
}
