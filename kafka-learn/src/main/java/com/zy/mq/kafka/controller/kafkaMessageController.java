package com.zy.mq.kafka.controller;

import com.zy.mq.kafka.service.KafkaMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class kafkaMessageController {

    @Autowired
    private KafkaMessageService kafkaMessageService;

    @GetMapping("/sendMessage")
    public ResponseEntity sendMessage() {
        return kafkaMessageService.sentMessage();
    }
}
