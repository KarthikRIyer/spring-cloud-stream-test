package com.example.scskafkatest;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerListener implements ProducerListener {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerListener.class);

    private static final StringDeserializer stringDeserializer = new StringDeserializer();

    @Override
    public void onSuccess(ProducerRecord record, RecordMetadata metadata) {
        logger.info("onSuccess: {}", stringDeserializer.deserialize(record.topic(), (byte[]) record.value()));
    }

    @Override
    public void onError(ProducerRecord record, RecordMetadata metadata, Exception e) {
        logger.info("onError: {}", record.value());
    }

}
