/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.repo;

import com.gamestore.catlogservice.entity.FeaturedType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author qbuser
 */
public interface FeaturedTypeRepo extends JpaRepository<FeaturedType, com.gamestore.catlogservice.enums.FeaturedType> {

}
