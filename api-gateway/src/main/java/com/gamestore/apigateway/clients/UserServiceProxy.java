/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.apigateway.clients;

import java.util.Set;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author qbuser
 */
@FeignClient(name = "user-service", url = "${zuul.routes.user-service.url}")
public interface UserServiceProxy {

    @PostMapping("users/{userId}/collection/{gameId}")
    public ResponseEntity<Boolean> addToCollection(
            @PathVariable("userId") Integer userId,
            @PathVariable("gameId") Integer gameId);

    @GetMapping("users/{userId}/collection")
    public Set<Integer> getUserCollection(
            @PathVariable("userId") Integer userId,
            @RequestParam("page") Integer page,
            @RequestParam("limit") Integer limit
    );

    @GetMapping("users/{userId}/wishlist")
    public Set<Integer> getUserWishlist(
            @PathVariable("userId") Integer userId,
            @RequestParam("page") Integer page,
            @RequestParam("limit") Integer limit
    );
}
