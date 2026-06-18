package com.innovation.transaction_system.transaction_ingestion_service.upload.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(DuplicateFileNameException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateFileName(DuplicateFileNameException e, HttpServletRequest request)
    {
        LinkedHashMap<String, Object> error = new LinkedHashMap<>();

        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.BAD_REQUEST);
        error.put("error", e.getMessage());
        error.put("path", request.getRequestURL());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
