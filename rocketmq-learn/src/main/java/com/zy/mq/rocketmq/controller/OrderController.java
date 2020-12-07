package com.zy.mq.rocketmq.controller;

import com.zy.mq.rocketmq.service.OrderService;
import com.zy.mq.rocketmq.service.MqMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MqMessageService orderlyMessageService;

    @GetMapping("/createOrder")
    public ResponseEntity createOrder() {
        return orderService.createOrder();
    }

    @GetMapping("/orderlyMessage")
    public ResponseEntity orderlyMessage() {
        return orderlyMessageService.sendSyncMessage();
    }

    @GetMapping("/orderlyAsyncMessage")
    public ResponseEntity orderlyAsyncMessage() {
        return orderlyMessageService.sendAsyncMessage();
    }
}
