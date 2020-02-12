/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.controller;

import com.gamestore.catlogservice.exception.ServerError;
import com.gamestore.catlogservice.service.DocumentService;
import com.gamestore.catlogservice.view.UploadFileResponseView;
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

    @PostMapping("/upload")
    public UploadFileResponseView saveDocument(@RequestParam("file") MultipartFile file) {
        String fileName = documentService.saveFile(file);
        if (fileName == null) {
            throw new ServerError("COULD_NOT_STORE_FILE");
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().
                path(fileName).toUriString();
        return new UploadFileResponseView(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());

    }
}
