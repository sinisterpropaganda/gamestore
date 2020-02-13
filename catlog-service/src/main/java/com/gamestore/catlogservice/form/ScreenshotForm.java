/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.form;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

/**
 *
 * @author qbuser
 */
public class ScreenshotForm {

    @NonNull
    private Integer gameId;
    @NotEmpty(message = "IMAGE_IDS_MUST_NOT_BE_NULL")
    private List<Integer> imageIds;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public List<Integer> getImageIds() {
        return imageIds;
    }

    public void setImageIds(List<Integer> imageIds) {
        this.imageIds = imageIds;
    }
}
