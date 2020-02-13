/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.service;

import com.gamestore.catlogservice.entity.Screenshot;
import java.util.List;

/**
 *
 * @author qbuser
 */
public interface ScreenshotService {

    public void saveAll(List<Screenshot> screenshots);
}
