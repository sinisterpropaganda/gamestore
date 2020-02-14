/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.service;

import com.gamestore.catlogservice.enums.FeaturedType;
import com.gamestore.catlogservice.form.FeaturedTypeForm;
import com.gamestore.catlogservice.form.GameForm;
import com.gamestore.catlogservice.form.ScreenshotForm;
import com.gamestore.catlogservice.view.BasicResponseView;
import com.gamestore.catlogservice.view.FeaturedTypeView;
import com.gamestore.catlogservice.view.GameView;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author qbuser
 */
public interface GameService {

    public GameView addGame(GameForm gameForm);

    public GameView getGame(Integer gameId);

    public BasicResponseView<Boolean> deleteGame(Integer gameId);

    public ResponseEntity<Boolean> addScreenshot(ScreenshotForm screenshotForm);

    public void addDescription(Integer gameId, String description);

    public ResponseEntity<FeaturedTypeView> addFeaturedType(FeaturedTypeForm form);

    public ResponseEntity<Boolean> deleteFeaturedType(FeaturedType type);

    public ResponseEntity<Boolean> addGameToFeatured(Integer gameId, FeaturedType type);
}
