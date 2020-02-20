/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.service;

import com.gamestore.userservice.form.UserRegisterForm;
import com.gamestore.userservice.view.UserView;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author qbuser
 */
public interface UserService {

    public ResponseEntity<UserView> registerUser(UserRegisterForm form);

    public ResponseEntity<UserView> getUser(Integer userId);
}
