/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.apigateway.clients;

import com.gamestore.apigateway.view.GameView;
import java.util.List;
import java.util.Set;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author qbuser
 */
@FeignClient(name = "catlog-service", url = "${zuul.routes.catlog-service.url}")
public interface CatalogServiceProxy {

    @PostMapping("games/{gameId}/buy")
    public ResponseEntity<Boolean> incrementBoughtCount(
            @PathVariable("gameId") Integer gameId);
    
    @PostMapping("games/collection")
    public List<GameView> getGames(Set<Integer> gameIds);
}
