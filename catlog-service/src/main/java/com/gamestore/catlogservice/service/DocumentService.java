/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.service;

import com.gamestore.catlogservice.entity.Document;
import java.util.List;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author qbuser
 */
public interface DocumentService {

    public void saveDocument(Document document);

    public void saveAllDocuments(Iterable<Document> documents);

    public Document getById(Integer documentId);

    public List<Document> getAllById(Set<Integer> ids);

    public Boolean existsById(Integer documentId);

    public String saveFile(MultipartFile file);

    public List<String> saveFiles(MultipartFile[] files);

}
