/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.serviceimpl;

import com.gamestore.catlogservice.entity.Document;
import com.gamestore.catlogservice.entity.Game;
import com.gamestore.catlogservice.entity.Screenshot;
import com.gamestore.catlogservice.enums.Status;
import com.gamestore.catlogservice.exception.NotFoundException;
import com.gamestore.catlogservice.form.GameForm;
import com.gamestore.catlogservice.form.ScreenshotForm;
import com.gamestore.catlogservice.repo.GameRepo;
import com.gamestore.catlogservice.service.DocumentService;
import com.gamestore.catlogservice.service.GameService;
import com.gamestore.catlogservice.service.ScreenshotService;
import com.gamestore.catlogservice.view.BasicResponseView;
import com.gamestore.catlogservice.view.GameView;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author qbuser
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepo gameRepo;
    @Autowired
    DocumentService documentService;
    @Autowired
    ScreenshotService screenshotService;

    @Override
    public GameView addGame(GameForm gameForm) {
        Document document = documentService.getById(gameForm.getIconId());
        document.setStatus(Status.ACTIVE);
        documentService.saveDocument(document);
        Game game = new Game(gameForm);
        gameRepo.save(game);
        return new GameView(game);
    }

    @Override
    public GameView getGame(Integer gameId) {
        Optional<Game> findById = gameRepo.findById(gameId);
        Game game = findById.orElseThrow(()
                -> new NotFoundException("GAME_NOT_FOUND"));
        return new GameView(game);
    }

    @Override
    public BasicResponseView<Boolean> deleteGame(Integer gameId) {
        if (!gameRepo.existsById(gameId)) {
            throw new NotFoundException("GAME_NOT_FOUND");
        }
        gameRepo.deleteById(gameId);
        return new BasicResponseView<>(Boolean.TRUE);
    }

    @Override
    public ResponseEntity<Boolean> addscreenshot(final ScreenshotForm screenshotForm) {
        screenshotForm.getImageIds().forEach(cnsmr -> {
            if (!documentService.existsById(cnsmr)) {
                throw new NotFoundException("IMAGE_NOT_FOUND");
            }
        });
        if (!gameRepo.existsById(screenshotForm.getGameId())) {
            throw new NotFoundException("GAME_NOT_FOUND");
        }

        Set<Integer> ids = new HashSet<>(screenshotForm.getImageIds());
        List<Document> documents = documentService.getAllById(ids);
        documents.forEach(cnsmr -> cnsmr.setStatus(Status.ACTIVE));
        List<Screenshot> screenshots = screenshotForm.getImageIds()
                .stream().map(imageId -> new Screenshot(screenshotForm.getGameId(), imageId))
                .collect(Collectors.toList());
        screenshotService.saveAll(screenshots);
        documentService.saveAllDocuments(documents);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
