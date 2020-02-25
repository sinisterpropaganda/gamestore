/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;

/**
 *
 * @author qbuser
 */
@Entity
@IdClass(BucketId.class)
public class Bucket implements Serializable {

    @Id
    private Integer userId;
    @Id
    private Integer gameId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date addDate;

    public Bucket() {
    }

    public Bucket(Integer userId, Integer gameId) {
        this.userId = userId;
        this.gameId = gameId;
        this.addDate = new Date(System.currentTimeMillis());
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}
