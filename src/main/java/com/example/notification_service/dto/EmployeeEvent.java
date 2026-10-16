package com.example.notification_service.dto;

public record EmployeeEvent(
        Long id,
        String name,
        String email,
        String department
) {}