/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.view;

import com.gamestore.userservice.enums.Role;
import com.gamestore.userservice.entity.GamestoreUser;
import com.gamestore.userservice.entity.Wishlist;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author qbuser
 */
public class UserView {

    private Integer userId;
    private String username;
    private Float balance;
    private Role role;
    private DocumentView documentView;
    private Set<Integer> wishlistedGames;

    public UserView(GamestoreUser user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.balance = user.getBalance();
        this.role = user.getUserRole();
        this.documentView = user.getDocument() != null
                ? new DocumentView(user.getDocument()) : null;
        this.wishlistedGames = user.getWishlists() != null
                ? user.getWishlists().stream().collect(
                        Collectors.mapping(Wishlist::getGameId, Collectors.toSet())) : null;
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

    public DocumentView getDocumentView() {
        return documentView;
    }

    public void setDocumentView(DocumentView documentView) {
        this.documentView = documentView;
    }

    public Set<Integer> getWishlistedGames() {
        return wishlistedGames;
    }

    public void setWishlistedGames(Set<Integer> wishlistedGames) {
        this.wishlistedGames = wishlistedGames;
    }
}
