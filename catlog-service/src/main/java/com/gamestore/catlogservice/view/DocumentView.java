/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.view;

import com.gamestore.catlogservice.entity.Document;
import com.gamestore.catlogservice.enums.Status;

/**
 *
 * @author qbuser
 */
public class DocumentView {

    private Integer documentId;
    private Status status;
    private String url;

    public DocumentView(Document document) {
        this.documentId = document.getDocumentId();
        this.status = document.getStatus();
        this.url = document.getUrl();
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
