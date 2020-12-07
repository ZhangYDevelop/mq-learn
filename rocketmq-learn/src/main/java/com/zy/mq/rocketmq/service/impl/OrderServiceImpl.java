package com.zy.mq.rocketmq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zy.entity.dto.OrderDto;
import com.zy.mq.rocketmq.service.OrderService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@SuppressWarnings("all")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private final Map<String, OrderDto> orderDtoList = new HashMap<>();

    @Override
    public ResponseEntity createOrder() {

        OrderDto orderDto = new OrderDto();
        orderDto.setPrice(78);
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setProductName("移动套餐");
        Message<String> message = MessageBuilder.withPayload(JSONObject.toJSONString(orderDto)).build();
        // 发送事务消息
        rocketMQTemplate.sendMessageInTransaction("pay_order_group", "pay_order_tx_topic", message, null);
        rocketMQTemplate.sendMessageInTransaction("logistics_group", "logistics_tx_topic", message, null);

        return  ResponseEntity.ok("订单处理中");
    }

    @Override
    public ResponseEntity saveOrder(OrderDto orderDto) {
        this.orderDtoList.put(orderDto.getOrderId(), orderDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<OrderDto> getOrder(String orderId) {
        return ResponseEntity.ok(orderDtoList.get(orderId));
    }

}
