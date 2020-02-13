package com.gamestore.catlogservice.entity;

import com.gamestore.catlogservice.form.ScreenshotForm;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author qbuser
 */
@Entity
@IdClass(ScreenshotId.class)
public class Screenshot implements Serializable {

    @Id
    private Integer gameId;
    @Id
    private Integer imageId;

    public Screenshot() {
    }

    public Screenshot(Integer gameId, Integer imageId) {
        this.gameId = gameId;
        this.imageId = imageId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
}
