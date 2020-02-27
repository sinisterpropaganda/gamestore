/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.entity;

import com.gamestore.catlogservice.enums.FeaturedTypeValue;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author qbuser
 */
public class FeaturedId implements Serializable {

    private com.gamestore.catlogservice.enums.FeaturedTypeValue type;
    private Integer gameId;

    public FeaturedId() {
    }

    public FeaturedId(FeaturedTypeValue type, Integer gameId) {
        this.type = type;
        this.gameId = gameId;
    }

    public FeaturedTypeValue getType() {
        return type;
    }

    public void setType(FeaturedTypeValue type) {
        this.type = type;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.gameId);
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
        final FeaturedId other = (FeaturedId) obj;
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.gameId, other.gameId)) {
            return false;
        }
        return true;
    }

}
