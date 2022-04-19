package com.example;

import com.example.utils.Consumer;
import com.example.utils.Deleter;
import com.example.utils.Processor;
import com.example.utils.Producer;

public class App {
    public static void main(String[] args) {
        // Clean topics
        Deleter.deleteTitlesTopic();

        // We send demo data
        Producer.generatePurchasesTopic();

        // We do different processing
        Processor.purchaseProcessorFilter();
        Processor.purchaseProcessorSplitFilter();

        // Finally we consume the processed data and display them
        Consumer.resumeConsumerWithMapAndCounts();

    }
}
