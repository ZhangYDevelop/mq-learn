package com.zy.mq.rocketmq.core;

import com.alibaba.fastjson.JSONObject;
import com.zy.mq.rocketmq.dto.OrderDTO;
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


@Component
@RocketMQTransactionListener(txProducerGroup = "pay_order_group" )
public class TransactionListenerLocal implements RocketMQLocalTransactionListener {


    private Logger logger = LoggerFactory.getLogger(TransactionListenerLocal.class);



    @Autowired
    private OrderService orderService;

    //执行本地事务
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(org.springframework.messaging.Message msg, Object arg) {
        String jsonString = new String((byte[])msg.getPayload());
        logger.info("执行本地事务,创建订单：{}", jsonString);
        OrderDTO orderDto = JSONObject.parseObject(jsonString, OrderDTO.class);
        try {
            orderService.saveOrder(orderDto);//模拟数据入库操作
            return  RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e) {
            return  RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    // mq回调检查本地事务执行情况
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        String jsonString = new String((byte[])msg.getPayload());
        logger.info("回调检查：{}", jsonString);

        OrderDTO temp = JSONObject.parseObject(jsonString, OrderDTO.class);
        String orderId = temp.getOrderId();
        if ( !StringUtils.isEmpty(orderId)) {
            OrderDTO orderDto = orderService.getOrder(orderId).getBody();
            if (null != orderDto) {
                return  RocketMQLocalTransactionState.COMMIT;
            } else {
                return  RocketMQLocalTransactionState.ROLLBACK;
            }

        } else {
            return  RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
