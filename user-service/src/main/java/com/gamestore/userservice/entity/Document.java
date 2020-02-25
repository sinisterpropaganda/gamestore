/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author qbuser
 */
@Entity
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer documentId;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    private String url;
    
    @OneToOne(mappedBy = "document")
    private GamestoreUser gamestoreUser;

    public Document() {
    }

    public Document(Status status, String url) {
        this.status = status;
        this.url = url;
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

    public GamestoreUser getGamestoreUser() {
        return gamestoreUser;
    }

    public void setGamestoreUser(GamestoreUser gamestoreUser) {
        this.gamestoreUser = gamestoreUser;
    }
}
