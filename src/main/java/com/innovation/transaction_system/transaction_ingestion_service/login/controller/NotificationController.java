package com.innovation.transaction_system.transaction_ingestion_service.login.controller;

import com.innovation.transaction_system.transaction_ingestion_service.login.entity.Notification;
import com.innovation.transaction_system.transaction_ingestion_service.login.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event")
public class NotificationController {

    @Autowired
    private NotificationRepository repository;

    @GetMapping("/{userId}")
    public List<Notification> getNotifications(
            @PathVariable Long userId) {

        return repository
                .findByUserId(userId);
    }
}
