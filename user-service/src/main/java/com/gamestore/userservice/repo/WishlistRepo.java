/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.repo;

import com.gamestore.userservice.entity.Wishlist;
import com.gamestore.userservice.entity.WishlistId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author qbuser
 */
public interface WishlistRepo extends JpaRepository<Wishlist, WishlistId> {

    public Page<Wishlist> findByUserId(Integer userId, Pageable pageable);
}
