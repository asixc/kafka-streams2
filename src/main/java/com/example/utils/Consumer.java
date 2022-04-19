package com.example.utils;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Properties;

public class Consumer {

    static Logger log = LoggerFactory.getLogger(Consumer.class);

    public static void resumeConsumerWithMapAndCounts() {
        Properties config = new Properties();
        config.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, Common.kafkaUrl);
        config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "consumer");
        config.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        config.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        StreamsBuilder builder = new StreamsBuilder();
        StreamsBuilder builder2 = new StreamsBuilder();


        log.info("====================================== CONSUMER ==========================================");

        // Consumir topic purchase para obtener nº de ventas y Dinero
        KStream<String, String> allPurchase = builder.stream(Common.purchasesTopic);

        allPurchase
            .foreach((key, value) -> {
                log.info("KEY -> System {}", key);
                log.info("KEY -> Purchase {}", value);
            });

        allPurchase.groupByKey().count().toStream()
                .peek((key, value) -> log.info("COUNT PRODUCTS OF [{}], PURCHASE: [{}]", key, value));

        allPurchase.mapValues(v -> v.split(" ")[3]).peek((key, value) -> log.info("PRECIO = {}", value));



        KafkaStreams streams = new KafkaStreams(builder.build(), config);

        streams.start();


        // Consumir topic purchase para obtener nº de ventas y Dinero
        KStream<String, String> androidPurchases = builder2.stream(Common.outputTopicAndroid);
        androidPurchases
                .foreach((key, value) -> {
                    log.info("======================================  ANDROID purchases  ==========================================");
                    log.info("key {}", key);
                    log.info("value {}", value);
                });

        KafkaStreams streams2 = new KafkaStreams(builder2.build(), config);

        streams2.start();

    }

    public static void resumeAndroid() {
        Properties config = new Properties();
        config.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, Common.kafkaUrl);
        config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "consumer2");
        config.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        config.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> androidPurchases = builder.stream(Common.outputTopicAndroid);

        androidPurchases
                .foreach((key, value) -> {
                    log.info("KEY -> System {}", key);
                    log.info("KEY -> Purchase {}", value);
                });

        KafkaStreams streams = new KafkaStreams(builder.build(), config);

        streams.start();
    }
}
