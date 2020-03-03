/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.apigateway.view;

import com.gamestore.apigateway.enums.FeaturedTypeValue;
import com.gamestore.apigateway.enums.Genre;
import com.gamestore.apigateway.enums.PlayableOn;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author qbuser
 */
public class GameView {

    private Integer gameId;
    private String publisher;
    private LocalDate releaseDate;
    private Float mrp;
    private Integer discountPercent;
    private Float price;
    private PlayableOn playableOn;
    private Genre genre;
    private Float fileSize;
    private String name;
    private Integer timesBought;
    private DocumentView documentView;
    private ScreenshotView screenshotView;
    private List<FeaturedTypeValue> featuredType;
    private String description;

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

    public DocumentView getDocumentView() {
        return documentView;
    }

    public void setDocumentView(DocumentView documentView) {
        this.documentView = documentView;
    }

    public ScreenshotView getScreenshotView() {
        return screenshotView;
    }

    public void setScreenshotView(ScreenshotView screenshotView) {
        this.screenshotView = screenshotView;
    }

    public List<FeaturedTypeValue> getFeaturedType() {
        return featuredType;
    }

    public void setFeaturedType(List<FeaturedTypeValue> featuredType) {
        this.featuredType = featuredType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
