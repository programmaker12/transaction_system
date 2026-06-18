package com.innovation.transaction_system.transaction_ingestion_service.service;

import com.innovation.transaction_system.transaction_ingestion_service.login.dto.LoginEventDTO;
import com.innovation.transaction_system.transaction_ingestion_service.login.dto.LoginRequestDTO;
import com.innovation.transaction_system.transaction_ingestion_service.login.dto.LoginResponseDTO;
import com.innovation.transaction_system.transaction_ingestion_service.login.entity.User;
import com.innovation.transaction_system.transaction_ingestion_service.login.repository.UserRepository;
import com.innovation.transaction_system.transaction_ingestion_service.login.security.JwtUtil;
import com.innovation.transaction_system.transaction_ingestion_service.login.service.LoginService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private KafkaTemplate<String, LoginEventDTO> kafkaTemplate;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    @InjectMocks
    private LoginService loginService;

    @Test
    void loginSuccessFromDatabase() {

        LoginRequestDTO request = new LoginRequestDTO();
        request.setUsername("admin");
        request.setPassword("admin123");

        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword("admin123");
        user.setRole("ADMIN");

        when(redisTemplate.opsForValue())
                .thenReturn(valueOperations);

        when(valueOperations.get("user:admin"))
                .thenReturn(null);

        when(userRepository.findByUsername("admin"))
                .thenReturn(Optional.of(user));

        when(jwtUtil.generateToken("admin"))
                .thenReturn("jwt-token");

        LoginResponseDTO response =
                loginService.login(request);

        assertEquals(
                "Login Successful",
                response.getMessage());

        assertEquals(
                "admin",
                response.getUsername());

        assertEquals(
                "ADMIN",
                response.getRole());

        assertEquals(
                "jwt-token",
                response.getToken());

        verify(userRepository)
                .findByUsername("admin");

        verify(valueOperations)
                .set(
                        eq("user:admin"),
                        eq(user),
                        eq(5L),
                        eq(TimeUnit.MINUTES)
                );
    }

    @Test
    void userNotFound() {

        LoginRequestDTO request =
                new LoginRequestDTO();

        request.setUsername("unknown");
        request.setPassword("password");

        when(redisTemplate.opsForValue())
                .thenReturn(valueOperations);

        when(valueOperations.get("user:unknown"))
                .thenReturn(null);

        when(userRepository.findByUsername("unknown"))
                .thenReturn(Optional.empty());

        LoginResponseDTO response =
                loginService.login(request);

        assertEquals(
                "User Not Found",
                response.getMessage());
    }

    @Test
    void invalidPassword() {

        LoginRequestDTO request =
                new LoginRequestDTO();

        request.setUsername("admin");
        request.setPassword("wrongpassword");

        User user = new User();

        user.setUsername("admin");
        user.setPassword("admin123");
        user.setRole("ADMIN");

        when(redisTemplate.opsForValue())
                .thenReturn(valueOperations);

        when(valueOperations.get("user:admin"))
                .thenReturn(user);

        LoginResponseDTO response =
                loginService.login(request);

        assertEquals(
                "Invalid Password",
                response.getMessage());
    }

    @Test
    void loginSuccessFromCache() {

        LoginRequestDTO request =
                new LoginRequestDTO();

        request.setUsername("admin");
        request.setPassword("admin123");

        User user = new User();

        user.setId(1L);
        user.setUsername("admin");
        user.setPassword("admin123");
        user.setRole("ADMIN");

        when(redisTemplate.opsForValue())
                .thenReturn(valueOperations);

        when(valueOperations.get("user:admin"))
                .thenReturn(user);

        when(jwtUtil.generateToken("admin"))
                .thenReturn("jwt-token");

        LoginResponseDTO response =
                loginService.login(request);

        assertEquals(
                "Login Successful",
                response.getMessage());

        verify(userRepository, never())
                .findByUsername(anyString());
    }

    @Test
    void kafkaFailureButLoginSuccess() {

        User user = new User();

        user.setUsername("admin");
        user.setPassword("admin123");
        user.setRole("ADMIN");

        when(redisTemplate.opsForValue())
                .thenReturn(valueOperations);

        when(valueOperations.get("user:admin"))
                .thenReturn(user);

        when(jwtUtil.generateToken("admin"))
                .thenReturn("jwt-token");

        LoginRequestDTO request =
                new LoginRequestDTO();

        request.setUsername("admin");
        request.setPassword("admin123");

        LoginResponseDTO response =
                loginService.login(request);

        assertEquals(
                "Login Successful",
                response.getMessage());
    }
}