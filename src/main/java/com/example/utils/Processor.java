package com.example.utils;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Processor {

    static Logger log = LoggerFactory.getLogger(Processor.class);

    public static void purchaseProcessorFilter() {
        log.info("====================================== PROCESSOR FILTER ==========================================");
        Properties config = new Properties();
        config.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, Common.kafkaUrl);
        config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, Common.appId);

        config.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        config.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());

        StreamsBuilder builder = new StreamsBuilder();

        // consume
        KStream<String, String> purchases = builder.stream(Common.purchasesTopic);

        // processing
        KStream<String, String> purchase = purchases
                .peek((key, value) -> log.info("ALL VALUES OF purchases :=====================>>> KEY [{}] --- VALUE [{}]", key, value))
                .filter((key, value) -> value.toLowerCase().contains("android")) // transformación 1
                .peek((key, value) -> log.info("k {} --- v {}", key, value))
                .filter((key, value) -> value.toLowerCase().contains("oneplus")) // transformación 2
                .peek((key, value) -> log.info("k {} --- v {}", key, value));

        // produce
        purchase.to(Common.outputTopicAndroidOneOPlus);

        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();
       // Common.doPause();
    }

    public static void purchaseProcessorSplitFilter() {
        log.info("====================================== PROCESSOR SPLIT FILTER ==========================================");

        Properties config = new Properties();
        config.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, Common.kafkaUrl);
        config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, Common.appId2);

        config.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        config.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        StreamsBuilder builder = new StreamsBuilder();

        // consume
        KStream<String, String> purchases = builder.stream(Common.purchasesTopic);

        // processing and produce
        purchases.split()
                .branch((key, value) -> value.toLowerCase().contains("apple"), Branched.withConsumer(kstream -> kstream.to(Common.outputTopicApple)))
                .branch((key, value) -> value.toLowerCase().contains("android"), Branched.withConsumer(kstream -> kstream.to(Common.outputTopicAndroid)))
                .defaultBranch(Branched.withConsumer(kstream -> kstream.to(Common.outputTopicOthers)));


        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();
        log.info("====================================== FIN ==========================================");
        // Common.doPause();
        Consumer.resumeAndroid();
    }


}
