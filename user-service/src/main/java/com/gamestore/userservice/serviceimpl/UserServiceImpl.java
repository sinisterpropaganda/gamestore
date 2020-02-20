/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.serviceimpl;

import com.gamestore.userservice.exception.ConflictException;
import com.gamestore.userservice.exception.NotFoundException;
import com.gamestore.userservice.form.UserRegisterForm;
import com.gamestore.userservice.repo.UserRepo;
import com.gamestore.userservice.security.entity.GamestoreUser;
import com.gamestore.userservice.service.UserService;
import com.gamestore.userservice.view.UserView;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author qbuser
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<UserView> registerUser(UserRegisterForm form) {
        GamestoreUser user = userRepo.findByUsername(form.getUsername());
        if (Objects.nonNull(user)) {
            throw new ConflictException("USERNAME_ALREADY_EXISTS");
        }
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        GamestoreUser gamestoreUser = new GamestoreUser(form, encodedPassword);
        userRepo.save(gamestoreUser);
        UserView view = new UserView(gamestoreUser);
        return ResponseEntity.ok(view);
    }

    @Override
    public ResponseEntity<UserView> getUser(Integer userId) {
        Optional<GamestoreUser> optional = userRepo.findById(userId);
        GamestoreUser user = optional.orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));
        UserView view = new UserView(user);
        return ResponseEntity.ok(view);
    }

}
