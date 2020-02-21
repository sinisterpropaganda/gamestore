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
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<UserView> registerUser(@Valid @RequestBody UserRegisterForm form) {
        return userService.registerUser(form);
    }

    @GetMapping("/users/register")
    public String sample() {
        return "response from server";
    }

    @GetMapping("api/users/{userId}")
    public ResponseEntity<UserView> getUser(@PathVariable Integer userId) {
        return userService.getUser(userId);
    }

}
