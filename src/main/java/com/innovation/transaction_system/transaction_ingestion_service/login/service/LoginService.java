package com.innovation.transaction_system.transaction_ingestion_service.login.service;


import com.innovation.transaction_system.transaction_ingestion_service.login.dto.LoginEventDTO;
import com.innovation.transaction_system.transaction_ingestion_service.login.dto.LoginRequestDTO;
import com.innovation.transaction_system.transaction_ingestion_service.login.dto.LoginResponseDTO;
import com.innovation.transaction_system.transaction_ingestion_service.login.entity.User;
import com.innovation.transaction_system.transaction_ingestion_service.login.repository.UserRepository;
import com.innovation.transaction_system.transaction_ingestion_service.login.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private KafkaTemplate<String, LoginEventDTO> kafkaTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public LoginResponseDTO login(LoginRequestDTO requestDTO) {

        LoginResponseDTO response =
                new LoginResponseDTO();

        String cacheKey = "user:" + requestDTO.getUsername();

        User user = (User) redisTemplate.opsForValue().get(cacheKey);

        if(user == null) {

            Optional<User> optionalUser =
                    userRepository.findByUsername(
                            requestDTO.getUsername());

            if(optionalUser.isEmpty()) {

                response.setMessage("User Not Found");
                return response;
            }

            user = optionalUser.get();

            redisTemplate.opsForValue().set(
                    cacheKey,
                    user,
                    5,
                    TimeUnit.MINUTES
            );
        }
        if(!user.getPassword()
                .equals(requestDTO.getPassword())) {

            response.setMessage("Invalid Password");

            return response;
        }

        String userName = user.getUsername();
        response.setMessage("Login Successful");

        response.setUsername(userName);

        response.setRole(user.getRole());
        LoginEventDTO event = new LoginEventDTO();
        event.setUserId(user.getId());
        event.setUsername(user.getUsername());
        event.setLoginTime(LocalDateTime.now());


        try {

            kafkaTemplate.send(
                    "user-login-topic",
                    event
            );

        } catch (Exception e) {

            System.err.println(
                    "Kafka publish failed: "
                            + e.getMessage()
            );


        }

        String token = jwtUtil.generateToken(userName);
        response.setToken(token);


        // Dummy validation
//        if ("admin".equals(requestDTO.getUsername())
//                && "admin123".equals(requestDTO.getPassword())) {
//
//            response.setMessage("Login Successful");
//            response.setUsername("admin");
//            response.setRole("ADMIN");
//            response.setToken("dummy-jwt-token");
//
//        } else {
//
//            response.setMessage("Invalid Credentials");
//        }

        return response;
    }
}
