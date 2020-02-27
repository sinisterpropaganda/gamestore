/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.view;

import com.gamestore.catlogservice.entity.FeaturedType;
import com.gamestore.catlogservice.enums.FeaturedTypeValue;

/**
 *
 * @author qbuser
 */
public class FeaturedTypeValueView {

    private FeaturedTypeValue featuredTypeValue;
    private String imageUrl;

    public FeaturedTypeValueView(FeaturedType featuredType) {
        this.featuredTypeValue = featuredType.getType();
        imageUrl = featuredType.getDocument().getUrl();
    }

    public FeaturedTypeValue getFeaturedTypeValue() {
        return featuredTypeValue;
    }

    public void setFeaturedTypeValue(FeaturedTypeValue featuredTypeValue) {
        this.featuredTypeValue = featuredTypeValue;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
