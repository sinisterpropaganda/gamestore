/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.view;

import com.gamestore.catlogservice.enums.FeaturedType;

/**
 *
 * @author qbuser
 */
public class FeaturedTypeView {

    private FeaturedType type;
    private String name;
    private Integer imageId;
    private DocumentView documentView;

    public FeaturedTypeView(com.gamestore.catlogservice.entity.FeaturedType type) {
        this.type = type.getType();
        this.name = type.getName();
        this.imageId = type.getImageId();
        this.documentView = type.getDocument() != null
                ? new DocumentView(type.getDocument()) : null;
    }

    public FeaturedType getType() {
        return type;
    }

    public void setType(FeaturedType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public DocumentView getDocumentView() {
        return documentView;
    }

    public void setDocumentView(DocumentView documentView) {
        this.documentView = documentView;
    }

}
