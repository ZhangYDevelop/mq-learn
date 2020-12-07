package com.zy.mq.rocketmq.service;

import org.springframework.http.ResponseEntity;

public interface MqMessageService {

    /**
     * 发送顺序消息
     * @return
     */
    ResponseEntity sendSyncMessage();

    ResponseEntity sendAsyncMessage();
}
