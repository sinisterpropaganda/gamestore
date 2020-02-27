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
import com.gamestore.catlogservice.enums.FeaturedTypeValue;
import com.gamestore.catlogservice.enums.Status;
import com.gamestore.catlogservice.exception.ConflictException;
import com.gamestore.catlogservice.exception.NotFoundException;
import com.gamestore.catlogservice.form.FeaturedTypeForm;
import com.gamestore.catlogservice.form.GameForm;
import com.gamestore.catlogservice.form.ScreenshotForm;
import com.gamestore.catlogservice.repo.DescriptionRepo;
import com.gamestore.catlogservice.repo.DocumentRepo;
import com.gamestore.catlogservice.repo.FeaturedRepo;
import com.gamestore.catlogservice.repo.FeaturedTypeRepo;
import com.gamestore.catlogservice.repo.GameRepo;
import com.gamestore.catlogservice.repo.ScreenshotRepo;
import com.gamestore.catlogservice.service.GameService;
import com.gamestore.catlogservice.view.BasicResponseView;
import com.gamestore.catlogservice.view.FeaturedTypeValueView;
import com.gamestore.catlogservice.view.FeaturedTypeView;
import com.gamestore.catlogservice.view.GameView;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author qbuser
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private FeaturedTypeRepo featuredTypeRepo;
    @Autowired
    private FeaturedRepo featuredRepo;
    @Autowired
    private DocumentRepo documentRepo;
    @Autowired
    private ScreenshotRepo screenshotRepo;
    @Autowired
    private DescriptionRepo descriptionRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);

    @Override
    public GameView addGame(final GameForm gameForm) {
        if (Objects.nonNull(gameForm.getIconId())) {
            Optional<Document> optional = documentRepo.findById(gameForm.getIconId());
            Document document = optional.orElseThrow(() -> new NotFoundException("DOCUMENT_NOT_FOUND"));
            if (document.getStatus() == Status.ACTIVE) {
                throw new ConflictException("IMAGE_ALREADY_USED");
            }
            document.setStatus(Status.ACTIVE);
            documentRepo.save(document);
        }
        Game game = new Game(gameForm);
        gameRepo.save(game);
        if (Objects.nonNull(gameForm.getDescription())
                && !gameForm.getDescription().isEmpty()) {
            this.addDescription(game.getGameId(), gameForm.getDescription());
        }
        if ((gameForm.getDiscountPercent() != null || gameForm.getDiscountPercent() != 0)
                && featuredTypeRepo.existsById(FeaturedTypeValue.DEALS)) {
            Featured featured = new Featured(FeaturedTypeValue.DEALS, game.getGameId());
            featuredRepo.save(featured);
        }
        if (gameForm.getFeaturedType() != null) {
            gameForm.getFeaturedType().forEach(featuredType -> {
                if (featuredType != FeaturedTypeValue.DEALS
                        && featuredTypeRepo.existsById(featuredType)) {
                    Featured featured = new Featured(featuredType, game.getGameId());
                    featuredRepo.save(featured);
                }
            });
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
        final List<Document> documents = new ArrayList<>();
        screenshotForm.getImageIds().forEach(cnsmr -> {
            Optional<Document> optional = documentRepo.findById(cnsmr);
            Document document = optional.orElseThrow(() -> new NotFoundException("IMAGE_NOT_FOUND"));
            if (document.getStatus() == Status.ACTIVE) {
                throw new ConflictException("IMAGE_ALREADY_USED");
            }
            document.setStatus(Status.ACTIVE);
            documents.add(document);
        });
        if (!gameRepo.existsById(screenshotForm.getGameId())) {
            throw new NotFoundException("GAME_NOT_FOUND");
        }

        List<Screenshot> screenshots = screenshotForm.getImageIds()
                .stream().map(imageId -> new Screenshot(screenshotForm.getGameId(), imageId))
                .collect(Collectors.toList());
        screenshotRepo.saveAll(screenshots);
        documentRepo.saveAll(documents);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Override
    public void addDescription(Integer gameId, String description) {
        if (!gameRepo.existsById(gameId)) {
            throw new NotFoundException("GAME_NOT_FOUND");
        }
        Optional<GameDesc> optional = descriptionRepo.findById(gameId);
        GameDesc gameDesc = optional.orElse(new GameDesc(gameId, description));
        gameDesc.setDescription(description);
        descriptionRepo.save(gameDesc);
    }

    @Override
    public ResponseEntity<FeaturedTypeView> addFeaturedType(FeaturedTypeForm form) {
        if (featuredTypeRepo.existsById(Enum.valueOf(FeaturedTypeValue.class, form.getType()))) {
            throw new ConflictException("FEATURED_TYPE_ALREADY_EXISTS");
        }
        Optional<Document> optional = documentRepo.findById(form.getImageId());
        Document document = optional.orElseThrow(() -> new NotFoundException("IMAGE_NOT_FOUND"));
        if (document.getStatus() == Status.ACTIVE) {
            throw new ConflictException("IMAGE_ALREADY_USED");
        }
        FeaturedType featuredType = new FeaturedType(form);
        featuredTypeRepo.save(featuredType);
        document.setStatus(Status.ACTIVE);
        documentRepo.save(document);
        return ResponseEntity.ok(new FeaturedTypeView(featuredType));
    }

    @Override
    public ResponseEntity<Boolean> deleteFeaturedType(com.gamestore.catlogservice.enums.FeaturedTypeValue type) {
        if (!featuredTypeRepo.existsById(type)) {
            throw new NotFoundException("FEATURED_TYPE_NOT_FOUND");
        }
        featuredTypeRepo.deleteById(type);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Override
    public ResponseEntity<Boolean> addGameToFeatured(Integer gameId, FeaturedTypeValue type) {
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

    @Override
    public ResponseEntity<List<FeaturedTypeValueView>> getAllFeaturedTypes() {
        List<FeaturedType> featuredTypes = featuredTypeRepo.findAll();
        List<FeaturedTypeValueView> valueViews = featuredTypes.stream().collect(Collectors.mapping(FeaturedTypeValueView::new, Collectors.toList()));
        return ResponseEntity.ok(valueViews);
    }

    @Override
    public ResponseEntity<List<GameView>> getAllGamesByFeatured(FeaturedTypeValue featuredType) {
        List<Featured> featureds = featuredRepo.findByType(featuredType);
        Set<Integer> collect = featureds.stream().mapToInt(tif -> tif.getGameId()).boxed().collect(Collectors.toSet());
        List<Game> games = gameRepo.findAllById(collect);
        List<GameView> gameViews = games.stream().collect(Collectors.mapping(GameView::new, Collectors.toList()));
        return ResponseEntity.ok(gameViews);
    }

    @Override
    public ResponseEntity<Page<List<Game>>> getUpcommingGames(Integer page, Integer limit) {
        LocalDate today = Instant.now().atZone(ZoneId.of("UTC")).toLocalDate();
        LocalDate startDate = today.plusDays(1);
        LocalDate endDate = today.plusDays(30);
        LOGGER.info("start_date:{}, end_date:{}", startDate, endDate);
        LOGGER.info("page:{}, limit:{}", page, limit);
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "releaseDate"));
        Page<List<Game>> games = gameRepo.findByReleaseDateBetween(startDate, endDate, pageRequest);
//        games.
        return ResponseEntity.ok(games);
    }

}
