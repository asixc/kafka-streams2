package com.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Common {
    static Logger log = LoggerFactory.getLogger(Processor.class);
    public static String appId = "stream-purchases";
    public static String appId2 = "stream-purchases-split";
    public static String kafkaUrl = "http://localhost:9092";
    public static String purchasesTopic = "purchases";
    public static String outputTopicApple = "billed-apple";
    public static String outputTopicAndroid = "billed-android";
    public static String outputTopicAndroidOneOPlus = "billed-android-oneplus";
    public static String outputTopicOthers = "billed-others";
    public static List<String> topicsToDelete = List.of(purchasesTopic, outputTopicApple, outputTopicAndroid, outputTopicAndroidOneOPlus, outputTopicOthers);
    public static void doPause() {
        log.info("====================================== PAUSE ==========================================");
        try {
            Thread.sleep(30000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
