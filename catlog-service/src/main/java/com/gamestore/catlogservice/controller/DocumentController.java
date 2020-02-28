/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.controller;

import com.gamestore.catlogservice.service.DocumentService;
import com.gamestore.catlogservice.view.UploadFileResponseView;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author qbuser
 */
@RestController
@RequestMapping("documents")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

    @PostMapping("/upload")
    public UploadFileResponseView saveDocument(@RequestParam("file") MultipartFile file) {
        String fileName = documentService.saveFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().
                path(fileName).toUriString();
        return new UploadFileResponseView(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
    @PostMapping("/upload-multiple")
    public List<UploadFileResponseView> saveDocuments(@RequestParam("files") MultipartFile[] files) {
        List<String> fileNames = documentService.saveFiles(files);
        List<String> collect = fileNames.stream().map(fnctn -> ServletUriComponentsBuilder.fromCurrentContextPath().
                path(fnctn).toUriString()).collect(Collectors.toList());
        return collect.stream().collect(Collectors.mapping(UploadFileResponseView::new, Collectors.toList()));
    }
}
