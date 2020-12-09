package com.zy.mq.rocketmq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.zy.mq.rocketmq.dto.OrderDTO;
import com.zy.mq.rocketmq.service.LogisticsService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "logistics_group",topic = "pay_order_tx_topic")
public class LogisticsListenerLocal implements RocketMQListener<String> {

    private Logger logger = LoggerFactory.getLogger(LogisticsListenerLocal.class);

    @Autowired
    private LogisticsService logisticsService;

    @Override
    public void onMessage(String message) {
        OrderDTO orderDto = JSONObject.parseObject(message, OrderDTO.class);
        logger.info("发起物流订单： {}", message);
        logisticsService.createLogistics(orderDto.getOrderId());
    }
}
