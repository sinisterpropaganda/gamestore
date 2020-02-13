/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.serviceimpl;

import com.gamestore.catlogservice.entity.Screenshot;
import com.gamestore.catlogservice.repo.ScreenshotRepo;
import com.gamestore.catlogservice.service.ScreenshotService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author qbuser
 */
@Service
public class ScreenshotServiceImpl implements ScreenshotService {

    @Autowired
    ScreenshotRepo screenshotRepo;

    @Override
    public void saveAll(List<Screenshot> screenshots) {
        screenshotRepo.saveAll(screenshots);
    }

}
