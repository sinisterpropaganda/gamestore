/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.controller;

import com.gamestore.catlogservice.enums.FeaturedTypeValue;
import com.gamestore.catlogservice.form.FeaturedTypeForm;
import com.gamestore.catlogservice.form.GameForm;
import com.gamestore.catlogservice.service.GameService;
import com.gamestore.catlogservice.view.BasicResponseView;
import com.gamestore.catlogservice.view.FeaturedTypeValueView;
import com.gamestore.catlogservice.view.FeaturedTypeView;
import com.gamestore.catlogservice.view.GameView;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("featured-types")
    public ResponseEntity<FeaturedTypeView> addFeaturedType(
            @RequestBody @Valid FeaturedTypeForm form) {
        return gameService.addFeaturedType(form);
    }

    @GetMapping("featured-types")
    public ResponseEntity<List<FeaturedTypeValueView>> getAllFeaturedTypes() {
        return gameService.getAllFeaturedTypes();
    }

    @DeleteMapping("featured-types/{type}")
    public ResponseEntity<Boolean> deleteFeaturedType(@PathVariable FeaturedTypeValue type) {
        return gameService.deleteFeaturedType(type);
    }

    @PostMapping("featured-types/add")
    public ResponseEntity<Boolean> addGameToFeaturedType(
            @RequestParam("type") FeaturedTypeValue type,
            @RequestParam("gameId") Integer gameId) {
        return gameService.addGameToFeatured(gameId, type);
    }

    @GetMapping("featured-types/{type}/list")
    public ResponseEntity<List<GameView>> getAllGamesByFeatured(
            @PathVariable FeaturedTypeValue type) {
        return gameService.getAllGamesByFeatured(type);
    }

    @GetMapping("upcomming")
    public ResponseEntity<Page<GameView>> getUpcommingGames(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "includeDescription", defaultValue = "false") Boolean includeDescription) {
        return gameService.getUpcommingGames(page, limit, includeDescription);
    }

    @GetMapping("latest")
    public ResponseEntity<Page<GameView>> getLatestReleases(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "includeDescription", defaultValue = "false") Boolean includeDescription) {
        return gameService.getLatestReleases(page, limit, includeDescription);
    }

    @GetMapping("best-sellers")
    public ResponseEntity<Page<GameView>> getBestSellers(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "includeDescription", defaultValue = "false") Boolean includeDescription) {
        return gameService.getBestSellers(page, limit, includeDescription);
    }

}
