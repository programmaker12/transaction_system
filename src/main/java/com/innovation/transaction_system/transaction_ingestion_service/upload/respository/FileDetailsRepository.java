package com.innovation.transaction_system.transaction_ingestion_service.upload.respository;

import com.innovation.transaction_system.transaction_ingestion_service.upload.entity.FileDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDetailsRepository extends JpaRepository<FileDetailsEntity, Long> {

    boolean existsByFileName(String filename);
}