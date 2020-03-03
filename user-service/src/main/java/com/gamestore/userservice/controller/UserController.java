/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.controller;

import com.gamestore.userservice.form.UserRegisterForm;
import com.gamestore.userservice.service.UserService;
import com.gamestore.userservice.view.UserView;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserView> registerUser(@Valid @RequestBody UserRegisterForm form) {
        return userService.registerUser(form);
    }

    @GetMapping("register")
    public String sample() {
        return "response from server";
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserView> getUser(@PathVariable Integer userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/{userId}/wishlist/{gameId}")
    public ResponseEntity<Boolean> addToWishlist(
            @PathVariable Integer userId,
            @PathVariable Integer gameId) {
        return userService.addWishlist(userId, gameId);
    }

    @DeleteMapping("/{userId}/wishlist/{gameId}")
    public ResponseEntity<Boolean> removeFromWishlist(
            @PathVariable Integer userId,
            @PathVariable Integer gameId) {
        return userService.removeWishlist(userId, gameId);
    }

    @GetMapping("{userId}/wishlist")
    public Set<Integer> getUserWishlist(@PathVariable Integer userId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        return userService.getUserWishlist(userId, page, limit);
    }

    @PostMapping("/{userId}/bucket/{gameId}")
    public ResponseEntity<Boolean> addToBucket(
            @PathVariable Integer userId,
            @PathVariable Integer gameId) {
        return userService.addToBucket(userId, gameId);
    }

    @DeleteMapping("/{userId}/bucket/{gameId}")
    public ResponseEntity<Boolean> removeFromBucket(
            @PathVariable Integer userId,
            @PathVariable Integer gameId) {
        return userService.removeFromBucket(userId, gameId);
    }

    @PostMapping("/{userId}/collection/{gameId}")
    public ResponseEntity<Boolean> addToCollection(
            @PathVariable Integer userId,
            @PathVariable Integer gameId) {
        System.out.println("add_to_collection_called");
        return userService.addToCollection(userId, gameId);
    }

    @GetMapping("{userId}/collection")
    public Set<Integer> getUserCollection(@PathVariable Integer userId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        return userService.getUserCollection(userId, page, limit);
    }
}
