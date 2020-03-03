/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.apigateway.controller;

import com.gamestore.apigateway.clients.CatalogServiceProxy;
import com.gamestore.apigateway.clients.UserServiceProxy;
import com.gamestore.apigateway.view.GameView;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author qbuser
 */
@RestController
public class GameController {

    @Autowired
    private CatalogServiceProxy catalogService;
    @Autowired
    private UserServiceProxy userService;

    @PostMapping("users/{userId}/collection/{gameId}")
    public ResponseEntity<Boolean> purchaseGame(
            @PathVariable Integer userId,
            @PathVariable Integer gameId) {
        userService.addToCollection(userId, gameId);
        catalogService.incrementBoughtCount(gameId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping("users/{userId}/collection")
    public List<GameView> getUsersCollection(@PathVariable Integer userId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        Set<Integer> gameIds = userService.getUserCollection(userId, page, limit);
        return catalogService.getGames(gameIds);
    }

    @GetMapping("users/{userId}/collection")
    public List<GameView> getUsersWishlist(@PathVariable Integer userId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        Set<Integer> gameIds = userService.getUserWishlist(userId, page, limit);
        return catalogService.getGames(gameIds);
    }
}
