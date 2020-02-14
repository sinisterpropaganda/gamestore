package com.gamestore.catlogservice.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    @ManyToOne
    @JoinColumn(name = "gameId", insertable = false, updatable = false)
    private Game game;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "imageId", insertable = false, updatable = false)
    private Document document;

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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document documents) {
        this.document = documents;
    }
}
