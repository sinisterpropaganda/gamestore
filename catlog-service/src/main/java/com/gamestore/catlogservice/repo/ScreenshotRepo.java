/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.repo;

import com.gamestore.catlogservice.entity.Screenshot;
import com.gamestore.catlogservice.entity.ScreenshotId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author qbuser
 */
public interface ScreenshotRepo extends JpaRepository<Screenshot, ScreenshotId> {

}
