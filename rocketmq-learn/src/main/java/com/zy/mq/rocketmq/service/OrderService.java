package com.zy.mq.rocketmq.service;

import com.zy.entity.dto.OrderDto;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity createOrder();

    ResponseEntity saveOrder(OrderDto orderDto);

    ResponseEntity<OrderDto> getOrder(String orderId);
}
