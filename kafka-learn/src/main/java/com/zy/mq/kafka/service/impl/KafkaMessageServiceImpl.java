package com.zy.mq.kafka.service.impl;

import com.zy.mq.kafka.service.KafkaMessageService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@SuppressWarnings("all")
public class KafkaMessageServiceImpl implements KafkaMessageService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public ResponseEntity sentMessage() {

        /**
         * public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
         *         if (keyBytes == null) {
         *             return stickyPartitionCache.partition(topic, cluster);
         *         }
         *         List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
         *         int numPartitions = partitions.size();
         *         // hash the keyBytes to choose a partition
         *         return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
         *     }
         */
        for (int i = 0; i < 10; i++) {
            // 通过KafkaTemplate 的send 方法，在指定key情况下，就会根据key进行计算，让后发送到分区，
            // 也就是说你指定的key是一样的，就会发送到同一个分区
            //  kafkaTemplate.send("kafkatopic2", "topic-key", "hello");
            kafkaTemplate.send("kafkatopic2", "hello");
        }
        return ResponseEntity.ok("ok");
    }
}
