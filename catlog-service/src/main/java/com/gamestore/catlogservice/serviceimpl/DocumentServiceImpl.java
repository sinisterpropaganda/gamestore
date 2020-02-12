/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.serviceimpl;

import com.gamestore.catlogservice.service.DocumentService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author qbuser
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Value(value = "${file.upload-dir}")
    private String destination;

    @Override
    public String saveFile(MultipartFile file) {
        try {
            String fileName = destination + "/" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException | IllegalStateException ex) {
            LOGGER.error("Document upload failed: {}", ex);
        }
        return null;
    }

}
