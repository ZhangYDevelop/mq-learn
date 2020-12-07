package com.zy.mq.rocketmq.core;

import com.alibaba.fastjson.JSONObject;
import com.zy.entity.dto.OrderDto;
import com.zy.mq.rocketmq.service.OrderService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
@RocketMQTransactionListener(txProducerGroup = "logistics_group" )
public class LogisticsListenerLocal implements RocketMQLocalTransactionListener {

    private final Map<String, OrderDto> orderDtoList = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(LogisticsListenerLocal.class);


    @Autowired
    private OrderService orderService;

    //执行本地事务
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String jsonString = new String((byte[])msg.getPayload());
        logger.info("执行本地事务,确认订单是否创建成功：{}", jsonString);
        OrderDto orderDto = JSONObject.parseObject(jsonString, OrderDto.class);
        String orderId = orderDto.getOrderId();
        if (!StringUtils.isEmpty(orderId)) {
            OrderDto dto = orderService.getOrder(orderId).getBody();
            if (null != dto) {
                return  RocketMQLocalTransactionState.COMMIT;
            } else {
                return  RocketMQLocalTransactionState.UNKNOWN;
            }

        } else {
            return  RocketMQLocalTransactionState.UNKNOWN;
        }
    }

    // mq回调检查本地事务执行情况
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        String jsonString = new String((byte[])msg.getPayload());
        logger.info("回调检查,确认订单是否创建成功：{}", jsonString);

        OrderDto temp = JSONObject.parseObject(jsonString, OrderDto.class);
        String orderId = temp.getOrderId();
        if ( !StringUtils.isEmpty(orderId)) {
            OrderDto dto = orderService.getOrder(orderId).getBody();
            if (null != dto) {
                return  RocketMQLocalTransactionState.COMMIT;
            } else {
                return  RocketMQLocalTransactionState.UNKNOWN;
            }

        } else {
            return  RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
