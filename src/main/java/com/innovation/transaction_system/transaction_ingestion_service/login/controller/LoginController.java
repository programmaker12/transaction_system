package com.innovation.transaction_system.transaction_ingestion_service.login.controller;

import com.innovation.transaction_system.transaction_ingestion_service.login.dto.LoginRequestDTO;
import com.innovation.transaction_system.transaction_ingestion_service.login.dto.LoginResponseDTO;
import com.innovation.transaction_system.transaction_ingestion_service.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO requestDTO) {

        LoginResponseDTO response =
                loginService.login(requestDTO);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }
}
