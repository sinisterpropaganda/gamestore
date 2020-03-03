/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.service;

import com.gamestore.userservice.form.UserRegisterForm;
import com.gamestore.userservice.view.UserView;
import java.util.Set;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author qbuser
 */
public interface UserService {

    public ResponseEntity<UserView> registerUser(UserRegisterForm form);

    public ResponseEntity<UserView> getUser(Integer userId);

    public ResponseEntity<Boolean> addWishlist(Integer userId, Integer gameId);

    public ResponseEntity<Boolean> removeWishlist(Integer userId, Integer gameId);

    public ResponseEntity<Boolean> addToBucket(Integer userId, Integer gameId);

    public ResponseEntity<Boolean> removeFromBucket(Integer userId, Integer gameId);

    public ResponseEntity<Boolean> addToCollection(Integer userId, Integer gameId);
    
    public Set<Integer> getUserCollection(Integer userId, Integer page, Integer limit);
    
    public Set<Integer> getUserWishlist(Integer userId, Integer page, Integer limit);
}
