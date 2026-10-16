package com.example.notification_service.consumer;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.notification_service.dto.EmployeeEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeNotificationConsumer {

    @KafkaListener(
            topics = "employee-created",
            groupId = "notification-group"
    )
    public void consume(EmployeeEvent employee) {

        String email = employee.email();

        if (EmailValidator.getInstance().isValid(email)) {
            log.info("Notification sent to: {}", email);
        } else {
            log.error("Invalid email received: {}", email);
        }
    }
}
