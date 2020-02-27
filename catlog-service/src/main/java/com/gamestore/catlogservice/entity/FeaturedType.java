/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.entity;

import com.gamestore.catlogservice.enums.FeaturedTypeValue;
import com.gamestore.catlogservice.form.FeaturedTypeForm;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author qbuser
 */
@Entity
public class FeaturedType implements Serializable {

    @Id
    @Enumerated(EnumType.ORDINAL)
    private FeaturedTypeValue type;
    private String name;
    private Integer imageId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "imageId", updatable = false, insertable = false)
    private Document document;

    public FeaturedType() {
    }

    public FeaturedType(FeaturedTypeForm form) {
        this.type = Enum.valueOf(FeaturedTypeValue.class, form.getType());
        this.name = form.getName();
        this.imageId = form.getImageId();
    }

    public FeaturedTypeValue getType() {
        return type;
    }

    public void setType(FeaturedTypeValue type) {
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

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
