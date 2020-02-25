/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.entity;

import com.gamestore.userservice.enums.Role;
import com.gamestore.userservice.form.UserRegisterForm;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author qbuser
 */
@Entity
public class GamestoreUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String password;
    private Integer profileImageId;
    private Float balance;
    @Enumerated(EnumType.ORDINAL)
    private Role userRole;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "profileImageId", insertable = false, updatable = false)
    private Document document;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private Set<Wishlist> wishlists;

    public GamestoreUser() {
    }

    public GamestoreUser(String username, String password, Integer profileImageId, Float balance, Role userRole) {
        this.username = username;
        this.password = password;
        this.profileImageId = profileImageId;
        this.balance = balance;
        this.userRole = userRole;
    }

    public GamestoreUser(UserRegisterForm form, String encodedPassword) {
        this.username = form.getUsername();
        this.password = encodedPassword;
        this.profileImageId = form.getProfileImageId();
        this.balance = form.getBalance() == null ? 0l : form.getBalance();
        this.userRole = form.getRole() == null ? Role.ROLE_USER : form.getRole();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getProfileImageId() {
        return profileImageId;
    }

    public void setProfileImageId(Integer profileImageId) {
        this.profileImageId = profileImageId;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Set<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(Set<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

}