/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.serviceimpl;

import com.gamestore.userservice.repo.UserRepo;
import com.gamestore.userservice.security.entity.GamestoreUser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author qbuser
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final GamestoreUser gamestoreUser = userRepo.findByUsername(username);
        GrantedAuthority authority = () -> gamestoreUser.getUserRole().toString();
        List<GrantedAuthority> authorities = new ArrayList();
        authorities.add(authority);
        return new User(gamestoreUser.getUsername(), gamestoreUser.getPassword(), authorities);
    }

}
