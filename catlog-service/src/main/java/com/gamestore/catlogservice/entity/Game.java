/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.entity;

import com.gamestore.catlogservice.enums.Genre;
import com.gamestore.catlogservice.enums.PlayableOn;
import com.gamestore.catlogservice.form.GameForm;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author qbuser
 */
@Entity
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameId;
    private String publisher;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date releaseDate;
    private Float mrp;
    private Integer discountPercent;
    private Float price;
    @Enumerated
    private PlayableOn playableOn;
    @Enumerated
    private Genre genre;
    private Float fileSize;
    private String name;
    private Integer timesBought;
    private Integer iconId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iconId", insertable = false, updatable = false)
    private Document document;

    @OneToMany(mappedBy = "game")
    private List<Screenshot> screenshot;

    public Game() {
    }

    public Game(GameForm gameForm) {
        this.publisher = gameForm.getPublisher();
        this.releaseDate = new Date(gameForm.getReleaseDate());
        this.mrp = gameForm.getMrp();
        this.discountPercent = gameForm.getDiscountPercent();
        this.price = discountPercent == null || discountPercent == 0 ? mrp : (mrp - (discountPercent / mrp) * 100);
        this.playableOn = gameForm.getPlayableOn();
        this.genre = gameForm.getGenre();
        this.fileSize = gameForm.getFileSize();
        this.name = gameForm.getName();
        this.timesBought = gameForm.getTimesBought() == null ? 0 : gameForm.getTimesBought();
        this.iconId = gameForm.getIconId();
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

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<Screenshot> getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(List<Screenshot> screenshot) {
        this.screenshot = screenshot;
    }

}
