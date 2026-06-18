package com.innovation.transaction_system.transaction_ingestion_service.upload.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadRequestDTO {

    private String corporateId;
    private MultipartFile file;
    private String uploadedBy;

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
}
