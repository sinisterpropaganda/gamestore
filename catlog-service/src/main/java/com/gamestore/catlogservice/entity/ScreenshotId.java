/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author qbuser
 */
public class ScreenshotId implements Serializable {

    private Integer gameId;
    private Integer imageId;

    public ScreenshotId() {
    }

    public ScreenshotId(Integer gameId, Integer imageId) {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.gameId);
        hash = 29 * hash + Objects.hashCode(this.imageId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ScreenshotId other = (ScreenshotId) obj;
        if (!Objects.equals(this.gameId, other.gameId)) {
            return false;
        }
        if (!Objects.equals(this.imageId, other.imageId)) {
            return false;
        }
        return true;
    }
}
