/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.view;

import com.gamestore.userservice.enums.Role;
import com.gamestore.userservice.security.entity.GamestoreUser;

/**
 *
 * @author qbuser
 */
public class UserView {

    private Integer userId;
    private String username;
    private Float balance;
    private Role role;

    public UserView(GamestoreUser user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.balance = user.getBalance();
        this.role = user.getUserRole();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
