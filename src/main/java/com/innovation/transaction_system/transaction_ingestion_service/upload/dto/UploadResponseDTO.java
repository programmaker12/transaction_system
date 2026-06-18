package com.innovation.transaction_system.transaction_ingestion_service.upload.dto;

public class UploadResponseDTO {



    private Long fileId;
    private String message;

    public UploadResponseDTO() {

    }
    public UploadResponseDTO(Long fileId, String message) {
        this.fileId = fileId;
        this.message = message;
    }
    public Long getFileId() {
        return fileId;
    }

    public String getMessage() {
        return message;
    }
}
