/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.service;

import com.gamestore.catlogservice.enums.FeaturedTypeValue;
import com.gamestore.catlogservice.form.FeaturedTypeForm;
import com.gamestore.catlogservice.form.GameForm;
import com.gamestore.catlogservice.form.ScreenshotForm;
import com.gamestore.catlogservice.view.BasicResponseView;
import com.gamestore.catlogservice.view.FeaturedTypeValueView;
import com.gamestore.catlogservice.view.FeaturedTypeView;
import com.gamestore.catlogservice.view.GameView;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author qbuser
 */
public interface GameService {

    public GameView addGame(GameForm gameForm);

    public GameView getGame(Integer gameId);

    public BasicResponseView<Boolean> deleteGame(Integer gameId);

    public ResponseEntity<FeaturedTypeView> addFeaturedType(FeaturedTypeForm form);

    public ResponseEntity<Boolean> deleteFeaturedType(FeaturedTypeValue type);

    public ResponseEntity<Boolean> addGameToFeatured(Integer gameId, FeaturedTypeValue type);

    public ResponseEntity<List<FeaturedTypeValueView>> getAllFeaturedTypes();

    public ResponseEntity<List<GameView>> getAllGamesByFeatured(FeaturedTypeValue featuredType);

    public ResponseEntity<Page<GameView>> getUpcommingGames(Integer page, Integer limit, Boolean includeDescription);
    
    public ResponseEntity<Page<GameView>> getLatestReleases(Integer page, Integer limit, Boolean includeDescription);
    
    public ResponseEntity<Page<GameView>> getBestSellers(Integer page, Integer limit, Boolean includeDescription);
}
