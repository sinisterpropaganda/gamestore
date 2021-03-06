/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.controller;

import com.gamestore.catlogservice.form.GameForm;
import com.gamestore.catlogservice.form.ScreenshotForm;
import com.gamestore.catlogservice.service.GameService;
import com.gamestore.catlogservice.view.BasicResponseView;
import com.gamestore.catlogservice.view.GameView;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author qbuser
 */
@RestController
@RequestMapping("games")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("{gameId}")
    public GameView getGame(@PathVariable Integer gameId) {
        return gameService.getGame(gameId);
    }

    @PostMapping
    public GameView addGame(@RequestBody @Valid GameForm gameForm) {
        return gameService.addGame(gameForm);
    }

    @DeleteMapping("{gameId}")
    public BasicResponseView<Boolean> deleteGame(@PathVariable Integer gameId) {
        return gameService.deleteGame(gameId);
    }

    @PostMapping("screenshots")
    public ResponseEntity<?> addScreenshot(@RequestBody @Valid ScreenshotForm form) {
        return gameService.addscreenshot(form);
    }

}
