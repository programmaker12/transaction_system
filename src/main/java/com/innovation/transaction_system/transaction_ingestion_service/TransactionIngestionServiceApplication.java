package com.innovation.transaction_system.transaction_ingestion_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class TransactionIngestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionIngestionServiceApplication.class, args);
	}

}
