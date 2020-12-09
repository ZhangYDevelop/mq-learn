package com.zy.mq.rocketmq.service;

public interface SmsService {

    /**
     * 发送短信
     * @return
     * @param orderId
     */
    boolean sentSms(String orderId);
}
