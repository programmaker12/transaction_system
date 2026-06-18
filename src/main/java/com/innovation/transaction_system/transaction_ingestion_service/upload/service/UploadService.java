package com.innovation.transaction_system.transaction_ingestion_service.upload.service;

import com.innovation.transaction_system.transaction_ingestion_service.upload.dto.UploadRequestDTO;
import com.innovation.transaction_system.transaction_ingestion_service.upload.dto.UploadResponseDTO;
import com.innovation.transaction_system.transaction_ingestion_service.upload.entity.FileDetailsEntity;
import com.innovation.transaction_system.transaction_ingestion_service.upload.exception.DuplicateFileNameException;
import com.innovation.transaction_system.transaction_ingestion_service.upload.respository.FileDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class UploadService {

    private final FileDetailsRepository repository;

    public UploadService(FileDetailsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UploadResponseDTO uploadFile(UploadRequestDTO request) throws IOException {

        MultipartFile file = request.getFile();

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        String fileName = file.getOriginalFilename();
        if (repository.existsByFileName(fileName)) {
            throw new DuplicateFileNameException("File name already exist");
        }

        FileDetailsEntity entity = new FileDetailsEntity();

        entity.setCorporateId(request.getCorporateId());
        entity.setFileName(file.getOriginalFilename());
        entity.setFileType(getFileExtension(file.getOriginalFilename()));
        entity.setFileData(file.getBytes()); // DB storage

        entity.setStatus("UPLOADED");
        entity.setUploadedBy(request.getUploadedBy());
        entity.setUploadedAt(LocalDateTime.now());

        entity.setTotalRecords(0);
        entity.setProcessedRecords(0);
        entity.setFailedRecords(0);

        repository.save(entity);

        return new UploadResponseDTO(entity.getId(), "File uploaded successfully");
    }

    private String getFileExtension(String fileName) {
        if (fileName == null) return null;
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }


}
