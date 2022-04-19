package com.example.utils;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Deleter {

    public static void deleteTitlesTopic() {
        Properties config = new Properties();
        config.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, Common.kafkaUrl);
        config.setProperty(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, String.valueOf(3000));
        config.setProperty(AdminClientConfig.DEFAULT_API_TIMEOUT_MS_CONFIG, String.valueOf(3000));
        AdminClient admin = AdminClient.create(config);
        try {
            admin.deleteTopics(Common.topicsToDelete).all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        admin.close();
    }
}
