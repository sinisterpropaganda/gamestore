/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author qbuser
 */
@Entity
@IdClass(FeaturedId.class)
public class Featured implements Serializable {

    @Id
    private com.gamestore.catlogservice.enums.FeaturedType type;
    @Id
    private Integer gameId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameId", insertable = false, updatable = false)
    Game game;

    public Featured() {
    }

    public Featured(com.gamestore.catlogservice.enums.FeaturedType type, Integer gameId) {
        this.type = type;
        this.gameId = gameId;
    }

    public com.gamestore.catlogservice.enums.FeaturedType getType() {
        return type;
    }

    public void setType(com.gamestore.catlogservice.enums.FeaturedType type) {
        this.type = type;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
