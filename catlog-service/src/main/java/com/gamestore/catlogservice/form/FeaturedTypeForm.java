/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.form;

import com.gamestore.catlogservice.constraint.ValidEnum;
import com.gamestore.catlogservice.enums.FeaturedTypeValue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.lang.NonNull;

/**
 *
 * @author qbuser
 */
public class FeaturedTypeForm {

    @NonNull
    @ValidEnum(enumClass = FeaturedTypeValue.class)
    private String type;
    @NotEmpty(message = "NAME_MUST_NOT_BE_NULL")
    @Size(max = 45, message = "NAME_INVALID_SIZE")
    private String name;
    @NonNull
    private Integer imageId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
}
