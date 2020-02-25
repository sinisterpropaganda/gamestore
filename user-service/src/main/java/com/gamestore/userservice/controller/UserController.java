/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.controller;

import com.gamestore.userservice.form.UserRegisterForm;
import com.gamestore.userservice.service.UserService;
import com.gamestore.userservice.view.UserView;
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
    public ResponseEntity<Boolean> addWishlist(
            @PathVariable Integer userId,
            @PathVariable Integer gameId) {
        return userService.addWishlist(userId, gameId);
    }

    @DeleteMapping("/{userId}/wishlist/{gameId}")
    public ResponseEntity<Boolean> removeWishlist(
            @PathVariable Integer userId,
            @PathVariable Integer gameId) {
        return userService.removeWishlist(userId, gameId);
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
        return userService.addToCollection(userId, gameId);
    }

    @DeleteMapping("/{userId}/collection/{gameId}")
    public ResponseEntity<Boolean> removeFromCollection(
            @PathVariable Integer userId,
            @PathVariable Integer gameId) {
        return userService.removeFromCollection(userId, gameId);
    }
}
