package com.sunyu.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yu 2018/11/9.
 */
@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "${kafka.consumer.topics:k8s_app}")
    public void listen(ConsumerRecord<String,String> record) {
       consumer(record);
    }

    /**
     * 监听批量发送的topic,批量消费
     */
    @KafkaListener(topics = "batch", containerFactory = "batchFactory")
    public void listen2(List<ConsumerRecord<String, String>> records) {
        batchConsumer(records);
    }


    /**
     * 单条消费
     */
    public void consumer(ConsumerRecord<String, String> record) {
        log.debug("主题:{}, 内容: {}", record.topic(), record.value());
    }


    /**
     * 批量消费
     */
    public void batchConsumer(List<ConsumerRecord<String, String>> records) {
        records.forEach(record -> consumer(record));
    }

}
