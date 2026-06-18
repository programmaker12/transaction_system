package com.innovation.transaction_system.transaction_ingestion_service.upload.exception;

public class DuplicateFileNameException extends RuntimeException {
    public DuplicateFileNameException(String message) {
        super(message);
    }
}
