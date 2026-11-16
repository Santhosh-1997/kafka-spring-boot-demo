package com.example.kafkademo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    private final KafkaTemplate<String, Order> kafkaTemplate;
    private final String topic;

    public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate,
                         @Value("${app.kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void send(Order order) {
        kafkaTemplate.send(topic, order.getOrderId(), order);
    }
}
