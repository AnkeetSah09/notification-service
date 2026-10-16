# Notification Service — Chapter 2

## Overview

The Notification Service is a Spring Boot microservice that consumes employee-created events from Apache Kafka. It receives employee details, validates the email format using Apache Commons Validator, and logs a notification message when the email is valid.

This project demonstrates asynchronous, event-driven communication between microservices.

## Technologies Used

- Java 17+
- Spring Boot
- Apache Kafka
- Spring Kafka
- Maven
- Apache Commons Validator
- Docker
- Lombok

## How It Works

1. The Employee Service creates an employee.
2. The Employee Service publishes employee details as a JSON event to the `employee-created` Kafka topic.
3. The Notification Service consumes the event using a Kafka listener.
4. The event is mapped to the `EmployeeEvent` record.
5. The consumer validates the email format.
6. A valid email produces a notification log; an invalid email produces an error log.

## Kafka Configuration

| Property | Value |
|---|---|
| Topic | `employee-created` |
| Consumer Group | `notification-group` |
| Bootstrap Server | `localhost:9092` |
| Message Format | JSON |

## Project Structure

```text
notification-service/
├── src/
│   ├── main/
│   │   ├── java/com/example/notification_service/
│   │   │   ├── consumer/
│   │   │   │   └── EmployeeNotificationConsumer.java
│   │   │   ├── dto/
│   │   │   │   └── EmployeeEvent.java
│   │   │   └── NotificationServiceApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Prerequisites

- Java 17 or later
- Docker
- Maven, or use the included Maven Wrapper
- Kafka running and accessible at `localhost:9092`

## Running the Application

### 1. Start Kafka

Start the Kafka container using the Docker Compose configuration from the Employee Service project. If Kafka is already running, do not start another broker.

### 2. Run the Notification Service

Open a terminal in the project root and execute:

```bash
.\mvnw.cmd spring-boot:run
```

Alternatively, run `NotificationServiceApplication` from your IDE.

## Testing the Kafka Consumer

Publish an event directly to the `employee-created` topic:

```bash
docker exec -i kafka-kraft kafka-console-producer --bootstrap-server localhost:9092 --topic employee-created
```

Enter this JSON message and press Enter:

```json
{"id":1001,"name":"Bruce Wayne","email":"bruce.wayne@example.com","department":"Security"}
```

### Expected Results

**Valid email:**

```text
Notification sent to: bruce.wayne@example.com
```

**Invalid email:**

```text
Invalid email received: tony@invalid
```

The messages above represent the expected application log output. The service currently simulates notifications through logs; it does not send actual emails.

## Key Learning Outcomes

- Consuming Kafka events using `@KafkaListener`
- Configuring a Kafka consumer group
- Deserializing JSON messages into a Java record
- Validating email formats using Apache Commons Validator
- Using Lombok's `@Slf4j` for logging
- Implementing asynchronous communication between microservices

## Chapter Status

Chapter 2 demonstrates a working Kafka consumer that receives employee events and validates email formats.
