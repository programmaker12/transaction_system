package com.innovation.transaction_system.transaction_ingestion_service.controller;


import com.innovation.transaction_system.transaction_ingestion_service.login.security.JwtFilter;
import com.innovation.transaction_system.transaction_ingestion_service.login.security.JwtUtil;
import com.innovation.transaction_system.transaction_ingestion_service.upload.controller.UploadController;
import com.innovation.transaction_system.transaction_ingestion_service.upload.dto.UploadResponseDTO;
import com.innovation.transaction_system.transaction_ingestion_service.upload.service.UploadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UploadController.class)
@AutoConfigureMockMvc(addFilters = false )
public class UploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UploadService uploadService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private JwtFilter jwtFilter;

    @Test
    void uploadSuccess() throws Exception {

        MockMultipartFile file =
                new MockMultipartFile(
                        "file",
                        "sample.xlsx",
                        "text/xlsx",
                        "id,name\n1,test".getBytes()
                );

        UploadResponseDTO response = new UploadResponseDTO(22L, "File Uploaded Successfully");


        when(uploadService.uploadFile(any()))
                .thenReturn(response);

        mockMvc.perform(
                multipart("/api/files/upload")
                        .file(file)
                        .param("corporateId", "CORP001")
                        .param("uploadedBy", "admin")
        )
                .andExpect(status().isOk());

        verify(uploadService, times(1))
                .uploadFile(any());
    }

}
