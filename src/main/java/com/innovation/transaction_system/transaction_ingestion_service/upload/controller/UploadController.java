package com.innovation.transaction_system.transaction_ingestion_service.upload.controller;


import com.innovation.transaction_system.transaction_ingestion_service.upload.dto.UploadRequestDTO;
import com.innovation.transaction_system.transaction_ingestion_service.upload.dto.UploadResponseDTO;
import com.innovation.transaction_system.transaction_ingestion_service.upload.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadResponseDTO> upload(
            @RequestParam("corporateId") String corporateId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy) throws IOException {

        UploadRequestDTO request = new UploadRequestDTO();
        request.setCorporateId(corporateId);
        request.setFile(file);
        request.setUploadedBy(uploadedBy);

        UploadResponseDTO response = uploadService.uploadFile(request);

        return ResponseEntity.ok(response);
    }
}
