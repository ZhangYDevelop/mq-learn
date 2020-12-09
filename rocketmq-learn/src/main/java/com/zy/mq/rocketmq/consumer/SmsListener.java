package com.zy.mq.rocketmq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.zy.mq.rocketmq.dto.OrderDTO;
import com.zy.mq.rocketmq.service.SmsService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "pay_order_group",topic = "logistics_tx_topic")
public class SmsListener implements RocketMQListener<String> {

    private Logger logger = LoggerFactory.getLogger(SmsListener.class);

    @Autowired
    private SmsService smsService;

    @Override
    public void onMessage(String message) {
        OrderDTO orderDto = JSONObject.parseObject(message, OrderDTO.class);
        logger.info("开始发起物流订单： {}", orderDto);
        smsService.sentSms(orderDto.getOrderId());
    }
}
