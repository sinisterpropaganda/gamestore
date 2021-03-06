/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.view;

import com.gamestore.catlogservice.entity.Game;
import com.gamestore.catlogservice.enums.Genre;
import com.gamestore.catlogservice.enums.PlayableOn;
import java.util.Date;

/**
 *
 * @author qbuser
 */
public class GameView {

    private Integer gameId;
    private String publisher;
    private Date releaseDate;
    private Float mrp;
    private Integer discountPercent;
    private Float price;
    private PlayableOn playableOn;
    private Genre genre;
    private Float fileSize;
    private String name;
    private Integer timesBought;
    private DocumentView documentView;

    public GameView(Game game) {
        this.gameId = game.getGameId();
        this.publisher = game.getPublisher();
        this.releaseDate = game.getReleaseDate();
        this.mrp = game.getMrp();
        this.discountPercent = game.getDiscountPercent();
        this.price = game.getPrice();
        this.playableOn = game.getPlayableOn();
        this.genre = game.getGenre();
        this.fileSize = game.getFileSize();
        this.name = game.getName();
        this.timesBought = game.getTimesBought();
        this.documentView = game.getDocument() != null
                ? new DocumentView(game.getDocument()) : null;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Float getMrp() {
        return mrp;
    }

    public void setMrp(Float mrp) {
        this.mrp = mrp;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public PlayableOn getPlayableOn() {
        return playableOn;
    }

    public void setPlayableOn(PlayableOn playableOn) {
        this.playableOn = playableOn;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Float getFileSize() {
        return fileSize;
    }

    public void setFileSize(Float fileSize) {
        this.fileSize = fileSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimesBought() {
        return timesBought;
    }

    public void setTimesBought(Integer timesBought) {
        this.timesBought = timesBought;
    }

    public DocumentView getDocumentView() {
        return documentView;
    }

    public void setDocumentView(DocumentView documentView) {
        this.documentView = documentView;
    }

}
