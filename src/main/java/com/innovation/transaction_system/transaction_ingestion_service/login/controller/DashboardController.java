package com.innovation.transaction_system.transaction_ingestion_service.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {

        return "Welcome Dashboard";
    }
}
