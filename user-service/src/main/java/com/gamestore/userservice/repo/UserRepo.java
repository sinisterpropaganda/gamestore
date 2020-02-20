/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.repo;

import com.gamestore.userservice.security.entity.GamestoreUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author qbuser
 */
public interface UserRepo extends JpaRepository<GamestoreUser, Integer> {

    public GamestoreUser findByUsername(String username);

}
