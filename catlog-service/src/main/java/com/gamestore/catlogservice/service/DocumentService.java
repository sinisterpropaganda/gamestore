/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.service;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author qbuser
 */
public interface DocumentService {

    public String saveFile(MultipartFile file);

}
