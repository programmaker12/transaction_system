package com.innovation.transaction_system.transaction_ingestion_service.service;

import com.innovation.transaction_system.transaction_ingestion_service.login.entity.User;
import com.innovation.transaction_system.transaction_ingestion_service.login.repository.UserRepository;
import com.innovation.transaction_system.transaction_ingestion_service.login.service.LoginService;
import com.innovation.transaction_system.transaction_ingestion_service.login.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void userFound() {

        User user = new User();
        user.setId(1L);
        user.setUsername("admin");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        User result =
                userService.getUser(1L);

        assertEquals(
                "admin",
                result.getUsername());

        verify(userRepository)
                .findById(1L);
    }

    @Test
    void userNotFound()
    {
        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> userService.getUser(1L));

        assertEquals("User Not Found", exception.getMessage());

        verify(userRepository)
                .findById(1L);
    }
}
