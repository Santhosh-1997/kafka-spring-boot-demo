package com.example.kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${app.kafka.topic}")
    public void consume(Order order) {
        log.info("Consumed order: id={}, product={}, quantity={}",
                order.getOrderId(), order.getProduct(), order.getQuantity());
    }
}
