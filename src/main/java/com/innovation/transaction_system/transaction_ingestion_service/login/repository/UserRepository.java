package com.innovation.transaction_system.transaction_ingestion_service.login.repository;

import com.innovation.transaction_system.transaction_ingestion_service.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
