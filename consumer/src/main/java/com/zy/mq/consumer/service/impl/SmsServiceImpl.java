package com.zy.mq.consumer.service.impl;

import com.zy.mq.consumer.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {
    private Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Override
    public boolean sentSms(String orderId) {
        logger.info("短信发送成功，orderId：{}", orderId );
        return true;
    }
}
