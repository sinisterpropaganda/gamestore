/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.repo;

import com.gamestore.catlogservice.entity.Featured;
import com.gamestore.catlogservice.entity.FeaturedId;
import com.gamestore.catlogservice.enums.FeaturedTypeValue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author qbuser
 */
public interface FeaturedRepo extends JpaRepository<Featured, FeaturedId> {

    List<Featured> findByType(FeaturedTypeValue featuredType);
}
