/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.form;

import com.gamestore.catlogservice.enums.Genre;
import com.gamestore.catlogservice.enums.PlayableOn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author qbuser
 */
public class GameForm {

    @NotEmpty
    @Size(max = 45, message = "PUBLISHER_INVALID_SIZE")
    private String publisher;
    @NotNull(message = "RELEASE_DATE_MUST_NOT_BE_NULL")
    private Long releaseDate;
    @NotNull(message = "MRP_MUST_NOT_BE_NULL")
    private Float mrp;
    private Integer discountPercent;
    private PlayableOn playableOn;
    private Genre genre;
    @NotNull(message = "FILE_SIZE_MUST_NOT_BE_NULL")
    private Float fileSize;
    @NotEmpty(message = "NAME_MUST_NOT_BE_NULL")
    @Size(max = 45, message = "NAME_INVALID_SIZE")
    private String name;
    private Integer timesBought;
    private Integer iconId;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Long releaseDate) {
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

    public void setIconId(Integer icon_id) {
        this.iconId = icon_id;
    }
}
