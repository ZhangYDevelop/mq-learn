package com.zy.mq.rocketmq.service;

import com.zy.mq.rocketmq.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity createOrder();

    ResponseEntity saveOrder(OrderDTO orderDto);

    ResponseEntity<OrderDTO> getOrder(String orderId);
}
