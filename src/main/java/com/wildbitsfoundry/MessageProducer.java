package com.wildbitsfoundry;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.UUID;

public class MessageProducer implements Runnable {

    private static final String TOPIC = "my-topic";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    @Override
    public void run() {

        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            UUID uuid = UUID.randomUUID();
            producer.send(new ProducerRecord<>(TOPIC, "1", "Test Message: " + uuid));
        } catch (Exception e) {
            System.out.println("Could not start producer: " + e);
        }
    }
}
