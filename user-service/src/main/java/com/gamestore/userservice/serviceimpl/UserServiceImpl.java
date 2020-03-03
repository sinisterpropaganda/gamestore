/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.userservice.serviceimpl;

import com.gamestore.userservice.entity.Bucket;
import com.gamestore.userservice.entity.BucketId;
import com.gamestore.userservice.entity.Collection;
import com.gamestore.userservice.entity.CollectionId;
import com.gamestore.userservice.exception.ConflictException;
import com.gamestore.userservice.exception.NotFoundException;
import com.gamestore.userservice.form.UserRegisterForm;
import com.gamestore.userservice.repo.UserRepo;
import com.gamestore.userservice.entity.GamestoreUser;
import com.gamestore.userservice.entity.Wishlist;
import com.gamestore.userservice.entity.WishlistId;
import com.gamestore.userservice.repo.BucketRepo;
import com.gamestore.userservice.repo.CollectionRepo;
import com.gamestore.userservice.repo.DocumentRepo;
import com.gamestore.userservice.repo.WishlistRepo;
import com.gamestore.userservice.service.UserService;
import com.gamestore.userservice.view.UserView;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author qbuser
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DocumentRepo documentRepo;
    @Autowired
    private WishlistRepo wishlistRepo;
    @Autowired
    private BucketRepo bucketRepo;
    @Autowired
    private CollectionRepo collectionRepo;

    @Override
    public ResponseEntity<UserView> registerUser(UserRegisterForm form) {
        GamestoreUser user = userRepo.findByUsername(form.getUsername());
        if (Objects.nonNull(user)) {
            throw new ConflictException("USERNAME_ALREADY_EXISTS");
        }
        if (form.getProfileImageId() != null
                && !documentRepo.existsById(form.getProfileImageId())) {
            throw new NotFoundException("IMAGE_NOT_FOUND");
        }
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        GamestoreUser gamestoreUser = new GamestoreUser(form, encodedPassword);
        userRepo.save(gamestoreUser);
        UserView view = new UserView(gamestoreUser);
        return ResponseEntity.ok(view);
    }

    @Override
    public ResponseEntity<UserView> getUser(Integer userId) {
        Optional<GamestoreUser> optional = userRepo.findById(userId);
        GamestoreUser user = optional.orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));
        UserView view = new UserView(user);
        return ResponseEntity.ok(view);
    }

    @Override
    public ResponseEntity<Boolean> addWishlist(Integer userId, Integer gameId) {
        if (!userRepo.existsById(userId)) {
            throw new NotFoundException("USER_NOT_FOUND");
        }
        WishlistId wishlistId = new WishlistId(gameId, userId);
        if (wishlistRepo.existsById(wishlistId)) {
            throw new ConflictException("GAME_ALREADY_IN_WISHLIST");
        }
        Wishlist wishlist = new Wishlist(gameId, userId);
        wishlistRepo.save(wishlist);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Override
    public ResponseEntity<Boolean> removeWishlist(Integer userId, Integer gameId) {
        WishlistId wishlistId = new WishlistId(gameId, userId);
        if (!wishlistRepo.existsById(wishlistId)) {
            throw new NotFoundException("GAME_NOT_IN_WISHLIST");
        }
        wishlistRepo.deleteById(wishlistId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Override
    public ResponseEntity<Boolean> addToBucket(Integer userId, Integer gameId) {
        if (!userRepo.existsById(userId)) {
            throw new NotFoundException("USER_NOT_FOUND");
        }
        BucketId bucketId = new BucketId(userId, gameId);
        if (bucketRepo.existsById(bucketId)) {
            throw new ConflictException("GAME_ALREADY_IN_BUCKET");
        }
        Bucket bucket = new Bucket(userId, gameId);
        bucketRepo.save(bucket);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Override
    public ResponseEntity<Boolean> removeFromBucket(Integer userId, Integer gameId) {
        BucketId bucketId = new BucketId(userId, gameId);
        if (!bucketRepo.existsById(bucketId)) {
            throw new NotFoundException("GAME_NOT_IN_BUCKET");
        }
        bucketRepo.deleteById(bucketId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Override
    public ResponseEntity<Boolean> addToCollection(Integer userId, Integer gameId) {
        if (!userRepo.existsById(userId)) {
            throw new NotFoundException("USER_NOT_FOUND");
        }
        CollectionId collectionId = new CollectionId(userId, gameId);
        if (collectionRepo.existsById(collectionId)) {
            throw new ConflictException("GAME_ALREADY_IN_COLLECTION");
        }
        collectionRepo.save(new Collection(userId, gameId));
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Override
    public Set<Integer> getUserCollection(Integer userId, Integer page, Integer limit) {
        if (!userRepo.existsById(userId)) {
            throw new NotFoundException("USER_NOT_FOUND");
        }
        PageRequest pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "purchaseDate");
        Page<Collection> collections = collectionRepo.findByUserId(userId, pageable);
        return collections.stream().mapToInt(Collection::getGameId).boxed().collect(Collectors.toSet());
    }

    @Override
    public Set<Integer> getUserWishlist(Integer userId, Integer page, Integer limit) {
        if (!userRepo.existsById(userId)) {
            throw new NotFoundException("USER_NOT_FOUND");
        }
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.Direction.DESC, "addDate");
        Page<Wishlist> wishlists = wishlistRepo.findByUserId(userId, pageRequest);
        return wishlists.stream().mapToInt(Wishlist::getGameId).boxed()
                .collect(Collectors.toSet());
    }

}
