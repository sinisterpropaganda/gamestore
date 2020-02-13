/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.serviceimpl;

import com.gamestore.catlogservice.entity.Document;
import com.gamestore.catlogservice.enums.Status;
import com.gamestore.catlogservice.exception.NotFoundException;
import com.gamestore.catlogservice.exception.ServerError;
import com.gamestore.catlogservice.repo.DocumentRepo;
import com.gamestore.catlogservice.service.DocumentService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    DocumentRepo documentRepo;

    @Override
    public String saveFile(MultipartFile file) {
        try {
            Document document = saveFileToDisk(file);
            documentRepo.save(document);
            return document.getUrl();
        } catch (IOException | IllegalStateException ex) {
            LOGGER.error("Document upload failed: {}", ex);
            throw new ServerError("FAILED_TO_STORE_FILE");
        }
    }

    @Override
    public List<String> saveFiles(MultipartFile[] files) {
        final List<Document> documents = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Document document = saveFileToDisk(file);
                documents.add(document);
            } catch (IOException ex) {
                LOGGER.error("Document upload failed: {}", ex);
                throw new ServerError("FAILED_TO_STORE_FILE");
            }
            documentRepo.saveAll(documents);
        }
        return documents.stream().map(fnctn -> fnctn.getUrl()).collect(Collectors.toList());
    }

    private Document saveFileToDisk(MultipartFile file) throws IOException {
        String fileName = destination + "/" + file.getOriginalFilename().replaceAll(" ", "_");
        Files.copy(file.getInputStream(), Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        return new Document(Status.INACTIVE, fileName);
    }

    @Override
    public void saveDocument(Document document) {
        documentRepo.save(document);
    }

    @Override
    public Boolean existsById(Integer documentId) {
        return documentRepo.existsById(documentId);
    }

    @Override
    public Document getById(Integer documentId) {
        Optional<Document> findById = documentRepo.findById(documentId);
        return findById.orElseThrow(() -> new NotFoundException("DOCUMENT_NOT_FOUND"));
    }

    @Override
    public List<Document> getAllById(Set<Integer> ids) {
        return documentRepo.findAllById(ids);
    }

    @Override
    public void saveAllDocuments(Iterable<Document> documents) {
        documentRepo.saveAll(documents);
    }

}
