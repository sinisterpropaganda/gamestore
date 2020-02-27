/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 *
 * @author qbuser
 */
@Entity
@IdClass(FeaturedId.class)
public class Featured implements Serializable {

    @Id
    private com.gamestore.catlogservice.enums.FeaturedTypeValue type;
    @Id
    private Integer gameId;

    public Featured() {
    }

    public Featured(com.gamestore.catlogservice.enums.FeaturedTypeValue type, Integer gameId) {
        this.type = type;
        this.gameId = gameId;
    }

    public com.gamestore.catlogservice.enums.FeaturedTypeValue getType() {
        return type;
    }

    public void setType(com.gamestore.catlogservice.enums.FeaturedTypeValue type) {
        this.type = type;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

}
