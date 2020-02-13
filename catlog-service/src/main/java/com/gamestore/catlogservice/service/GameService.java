/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.service;

import com.gamestore.catlogservice.form.GameForm;
import com.gamestore.catlogservice.form.ScreenshotForm;
import com.gamestore.catlogservice.view.BasicResponseView;
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
    
    public ResponseEntity<Boolean> addscreenshot(ScreenshotForm screenshotForm);
}
