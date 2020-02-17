/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.serviceimpl;

import com.gamestore.catlogservice.entity.Document;
import com.gamestore.catlogservice.entity.Featured;
import com.gamestore.catlogservice.entity.FeaturedId;
import com.gamestore.catlogservice.entity.FeaturedType;
import com.gamestore.catlogservice.entity.Game;
import com.gamestore.catlogservice.entity.GameDesc;
import com.gamestore.catlogservice.entity.Screenshot;
import com.gamestore.catlogservice.enums.Status;
import com.gamestore.catlogservice.exception.ConflictException;
import com.gamestore.catlogservice.exception.NotFoundException;
import com.gamestore.catlogservice.form.FeaturedTypeForm;
import com.gamestore.catlogservice.form.GameForm;
import com.gamestore.catlogservice.form.ScreenshotForm;
import com.gamestore.catlogservice.repo.DescriptionRepo;
import com.gamestore.catlogservice.repo.FeaturedRepo;
import com.gamestore.catlogservice.repo.FeaturedTypeRepo;
import com.gamestore.catlogservice.repo.GameRepo;
import com.gamestore.catlogservice.repo.ScreenshotRepo;
import com.gamestore.catlogservice.service.DocumentService;
import com.gamestore.catlogservice.service.GameService;
import com.gamestore.catlogservice.view.BasicResponseView;
import com.gamestore.catlogservice.view.FeaturedTypeView;
import com.gamestore.catlogservice.view.GameView;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
    FeaturedTypeRepo featuredTypeRepo;
    @Autowired
    FeaturedRepo featuredRepo;
    @Autowired
    DocumentService documentService;
    @Autowired
    ScreenshotRepo screenshotRepo;
    @Autowired
    DescriptionRepo descriptionRepo;

    @Override
    public GameView addGame(GameForm gameForm) {
        Document document = documentService.getById(gameForm.getIconId());
        document.setStatus(Status.ACTIVE);
        documentService.saveDocument(document);
        Game game = new Game(gameForm);
        gameRepo.save(game);
        if (Objects.nonNull(gameForm.getDescription())
                && !gameForm.getDescription().isEmpty()) {
            this.addDescription(game.getGameId(), gameForm.getDescription());
        }
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
        if (descriptionRepo.existsById(gameId)) {
            descriptionRepo.deleteById(gameId);
        }
        gameRepo.deleteById(gameId);
        return new BasicResponseView<>(Boolean.TRUE);
    }

    @Override
    public ResponseEntity<Boolean> addScreenshot(final ScreenshotForm screenshotForm) {
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
        screenshotRepo.saveAll(screenshots);
        documentService.saveAllDocuments(documents);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Override
    public void addDescription(Integer gameId, String description) {
        if (!gameRepo.existsById(gameId)) {
            throw new NotFoundException("GAME_NOT_FOUND");
        }
        GameDesc gameDesc = new GameDesc(gameId, description);
        descriptionRepo.save(gameDesc);
    }

    @Override
    public ResponseEntity<FeaturedTypeView> addFeaturedType(FeaturedTypeForm form) {
        if (featuredTypeRepo.existsById(Enum.valueOf(com.gamestore.catlogservice.enums.FeaturedType.class, form.getType()))) {
            throw new ConflictException("FEATURED_TYPE_ALREADY_EXISTS");
        }

        if (!documentService.existsById(form.getImageId())) {
            throw new NotFoundException("IMAGE_NOT_FOUND");
        }
        FeaturedType featuredType = new FeaturedType(form);
        featuredTypeRepo.save(featuredType);
        return ResponseEntity.ok(new FeaturedTypeView(featuredType));
    }

    @Override
    public ResponseEntity<Boolean> deleteFeaturedType(com.gamestore.catlogservice.enums.FeaturedType type) {
        if (!featuredTypeRepo.existsById(type)) {
            throw new NotFoundException("FEATURED_TYPE_NOT_FOUND");
        }
        featuredTypeRepo.deleteById(type);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Override
    public ResponseEntity<Boolean> addGameToFeatured(Integer gameId, com.gamestore.catlogservice.enums.FeaturedType type) {
        if (!featuredTypeRepo.existsById(type)) {
            throw new NotFoundException("FEATURED_TYPE_NOT_FOUND");
        }
        if (!gameRepo.existsById(gameId)) {
            throw new NotFoundException("GAME_NOT_FOUND");
        }
        if (featuredRepo.existsById(new FeaturedId(type, gameId))) {
            throw new ConflictException("GAME_ALREADY_ADDED");
        }
        Featured featured = new Featured(type, gameId);
        featuredRepo.save(featured);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
