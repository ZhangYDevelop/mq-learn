package com.zy.mq.consumer.listener.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.zy.entity.dto.OrderDto;
import com.zy.mq.consumer.service.SmsService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "pay_order_group",topic = "logistics_tx_topic")
public class SmsListenerLocal implements RocketMQListener<String> {

    private Logger logger = LoggerFactory.getLogger(SmsListenerLocal.class);

    @Autowired
    private SmsService smsService;

    @Override
    public void onMessage(String message) {
        OrderDto orderDto = JSONObject.parseObject(message, OrderDto.class);
        logger.info("开始发起物流订单： {}", orderDto);
        smsService.sentSms(orderDto.getOrderId());
    }
}
