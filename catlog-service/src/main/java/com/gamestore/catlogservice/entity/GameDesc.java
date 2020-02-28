/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author qbuser
 */
@Entity
public class GameDesc implements Serializable {

    @Id
    private Integer gameId;
    private String description;

    public GameDesc() {
    }

    public GameDesc(Integer gameId, String description) {
        this.gameId = gameId;
        this.description = description;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.gameId);
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
        final GameDesc other = (GameDesc) obj;
        if (!Objects.equals(this.gameId, other.gameId)) {
            return false;
        }
        return true;
    }
}
