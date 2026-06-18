//package com.innovation.transaction_system.transaction_ingestion_service.login.service;
//
//import com.innovation.transaction_system.transaction_ingestion_service.login.dto.LoginEventDTO;
//import com.innovation.transaction_system.transaction_ingestion_service.login.entity.Notification;
//import com.innovation.transaction_system.transaction_ingestion_service.login.repository.NotificationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class NotificationConsumer {
//
//    @Autowired
//    private NotificationRepository repository;
//
//    @KafkaListener(
//            topics = "user-login-topic",
//            groupId = "notification-group")
//    public void consume(LoginEventDTO event) {
//
//        Notification notification =
//                new Notification();
//
//        notification.setUserId(
//                event.getUserId());
//
//        notification.setTitle(
//                "Login Successful");
//
//        notification.setMessage(
//                "Welcome back "
//                        + event.getUsername());
//
//        notification.setRead(false);
//
//        notification.setCreatedAt(
//                LocalDateTime.now());
//
//        repository.save(notification);
//    }
//}
