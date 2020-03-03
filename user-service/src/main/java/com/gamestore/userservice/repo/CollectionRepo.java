/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.repo;

import com.gamestore.userservice.entity.Collection;
import com.gamestore.userservice.entity.CollectionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author qbuser
 */
public interface CollectionRepo extends JpaRepository<Collection, CollectionId> {

    public Page<Collection> findByUserId(Integer userId, Pageable pageable);
}
