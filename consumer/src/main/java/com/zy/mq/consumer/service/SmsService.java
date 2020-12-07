package com.zy.mq.consumer.service;

public interface SmsService {

    /**
     * 发送短信
     * @return
     * @param orderId
     */
    boolean sentSms(String orderId);
}
