package com.example.utils;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class Producer {
    static Logger log = LoggerFactory.getLogger(Producer.class);
    public static void generatePurchasesTopic() {
        log.info("====================================== PRODUCER ==========================================");
        Properties config = new Properties();
        config.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Common.kafkaUrl);
        config.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(config);

        List.of(
                        "purchase iphone Apple 1460.30",
                        "purchase OnePlus Android 909.09",
                        "purchase Motorola Android 101.50",
                        "purchase iphone Apple 1460.30",
                        "purchase OnePlus Android 909.09",
                        "purchase Xiaomi Linux 101.99",
                        "purchase OnePlusNord Android 303.09")
                .stream()
                .forEach(
                        msg -> producer.send(new ProducerRecord<>(Common.purchasesTopic, msg.split(" ")[2], msg))
                );
        // Common.doPause();

    }
}
