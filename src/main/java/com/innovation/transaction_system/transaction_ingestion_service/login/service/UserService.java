package com.innovation.transaction_system.transaction_ingestion_service.login.service;


import com.innovation.transaction_system.transaction_ingestion_service.login.entity.User;
import com.innovation.transaction_system.transaction_ingestion_service.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));
    }
}
