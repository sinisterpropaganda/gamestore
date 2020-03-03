/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 *
 * @author qbuser
 */
@Entity
@IdClass(CollectionId.class)
public class Collection implements Serializable {

    @Id
    private Integer userId;
    @Id
    private Integer gameId;
    private LocalDate purchaseDate;

    public Collection() {
    }

    public Collection(Integer userId, Integer gameId) {
        this.userId = userId;
        this.gameId = gameId;
        this.purchaseDate = LocalDate.now();
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
