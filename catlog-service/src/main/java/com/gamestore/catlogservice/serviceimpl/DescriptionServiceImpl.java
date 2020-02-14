/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.serviceimpl;

import com.gamestore.catlogservice.entity.GameDesc;
import com.gamestore.catlogservice.repo.DescriptionRepo;
import com.gamestore.catlogservice.service.DescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author qbuser
 */
@Service
public class DescriptionServiceImpl implements DescriptionService {

    @Autowired
    DescriptionRepo descriptionRepo;

    @Override
    public void saveDescription(Integer gameId, String description) {
        GameDesc gameDesc = new GameDesc(gameId, description);
        descriptionRepo.save(gameDesc);
    }

}
