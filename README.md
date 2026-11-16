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

Open [Kafka UI](http://localhost:8081) to inspect the local Kafka cluster. It shows topics, partitions, messages, consumer groups, and consumer lag.

The Spring Boot application health endpoint is available at [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health). It reports whether the application can reach Kafka and includes the application’s health details.

In another terminal:

```bash
curl -X POST http://localhost:8080/orders \
  -H 'Content-Type: application/json' \
  -d '{"orderId":"order-1001","product":"keyboard","quantity":2}'
```

Watch the Spring Boot terminal for the `Consumed order` log entry.

## What each status means

- **Producer/application:** check `/actuator/health`; the REST response also confirms that the producer accepted the order for sending.
- **Topic:** in Kafka UI, open `Topics → orders` to view partitions, offsets, and messages.
- **Consumer:** in Kafka UI, open `Consumer Groups → kafka-learning-group` to view members, offsets, and lag.
- **End-to-end flow:** publish an order, then watch the topic message and the consumer lag return to zero.

## Suggested experiments

1. Stop the application, send messages with a Kafka CLI, then restart it and observe `auto-offset-reset=earliest`.
2. Change the consumer `group-id` and see how a new group receives the topic history.
3. Change the topic partition count and experiment with keys and message ordering.
4. Add a second `@KafkaListener` with a different group ID to create another independent consumer.

The demo uses Spring Boot 2.7 so it can run with Java 11. For new production applications, consider upgrading to a current Spring Boot release and Java LTS version.
