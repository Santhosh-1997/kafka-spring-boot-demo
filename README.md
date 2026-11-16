# Spring Boot Kafka Learning Demo

This is a small end-to-end example of a Spring Boot application publishing and consuming JSON messages with Apache Kafka.

## What to learn

`POST /orders` → `OrderProducer` → Kafka topic `orders` → `OrderConsumer`

- `KafkaTopicConfig` creates the topic when the application starts.
- `OrderProducer` sends an `Order` as JSON, using `orderId` as the Kafka message key.
- `OrderConsumer` listens in consumer group `kafka-learning-group` and logs the message.

## Run it

Requirements: Java 11+, Maven, and Docker.

```bashcd kafka-spring-boot-demodocker compose up -d


cd kafka-spring-boot-demo
docker compose up -d
mvn spring-boot:run
```

In another terminal:

```bash
curl -X POST http://localhost:8080/orders \
  -H 'Content-Type: application/json' \
  -d '{"orderId":"order-1001","product":"keyboard","quantity":2}'
```

Watch the Spring Boot terminal for the `Consumed order` log entry.

## Suggested experiments

1. Stop the application, send messages with a Kafka CLI, then restart it and observe `auto-offset-reset=earliest`.
2. Change the consumer `group-id` and see how a new group receives the topic history.
3. Change the topic partition count and experiment with keys and message ordering.
4. Add a second `@KafkaListener` with a different group ID to create another independent consumer.

The demo uses Spring Boot 2.7 so it can run with Java 11. For new production applications, consider upgrading to a current Spring Boot release and Java LTS version.
