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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
    private LocalDate releaseDate;
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "iconId", insertable = false, updatable = false)
    private Document document;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gameId", insertable = false, updatable = false)
    private List<Screenshot> screenshot;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gameId", insertable = false, updatable = false)
    private List<Featured> featured;

    public Game() {
    }

    public Game(GameForm gameForm) {
        this.publisher = gameForm.getPublisher();
        this.releaseDate = Instant.ofEpochMilli(gameForm.getReleaseDate())
                .atZone(ZoneId.of("UTC"))
                .toLocalDate();
        this.mrp = gameForm.getMrp();
        this.discountPercent = gameForm.getDiscountPercent();
        this.price = discountPercent == null || discountPercent == 0 ? mrp : (mrp - (discountPercent / mrp) * 100);
        this.playableOn = Enum.valueOf(PlayableOn.class, gameForm.getPlayableOn());
        this.genre = Enum.valueOf(Genre.class, gameForm.getGenre());
        this.fileSize = gameForm.getFileSize();
        this.name = gameForm.getName();
        this.timesBought = 0;
        this.iconId = gameForm.getIconId();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
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

    public List<Featured> getFeatured() {
        return featured;
    }

    public void setFeatured(List<Featured> featured) {
        this.featured = featured;
    }

}
