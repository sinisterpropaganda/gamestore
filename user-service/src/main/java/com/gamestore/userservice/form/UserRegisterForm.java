/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.form;

import com.gamestore.userservice.enums.Role;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author qbuser
 */
public class UserRegisterForm {

    @NotEmpty(message = "USERNAME_MUST_NOT_BE_NULL")
    @Size(max = 45, message = "USERNAME_INVALID_SIZE")
    private String username;
    @NotEmpty(message = "PASSWORD_MUST_NOT_BE_NULL")
    @Size(message = "PASSWORD_INVALID_SIZE")
    private String password;
    private Integer profileImageId;
    private Float balance;
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
