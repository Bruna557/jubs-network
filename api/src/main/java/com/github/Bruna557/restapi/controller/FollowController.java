package com.github.Bruna557.restapi.controller;

import com.github.Bruna557.restapi.exception.ResourceNotFoundException;
import com.github.Bruna557.restapi.model.Follow;
import com.github.Bruna557.restapi.model.User;
import com.github.Bruna557.restapi.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FollowController {
    @Autowired
    FollowRepository followRepository;

    @PreAuthorize("#userId == authentication.principal.userId")
    @PostMapping("user/{userId}/follow/{followedId}")
    ResponseEntity<Follow> follow(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "followedId") Long followedId) {
        return ResponseEntity.ok(followRepository.save(new Follow(userId, followedId)));
    }

    @PreAuthorize("#userId == authentication.principal.userId")
    @DeleteMapping("user/{userId}/unfollow/{followedId}")
    ResponseEntity<Boolean> unfollow(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "followedId") Long followedId) throws ResourceNotFoundException {
        Follow follow =
                followRepository
                        .findByFollowerIdAndFollowedId(userId, followedId)
                        .orElseThrow(() -> new ResourceNotFoundException("Following not found"));

        followRepository.delete(follow);
        return ResponseEntity.ok(true);
    }

    @PreAuthorize("#userId == authentication.principal.userId")
    @GetMapping("user/{userId}/followed")
    ResponseEntity<List<User>> getFollowed(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(followRepository.findFollowed(userId));
    }

    @PreAuthorize("#userId == authentication.principal.userId")
    @GetMapping("user/{userId}/followers")
    ResponseEntity<List<User>> getFollowers(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(followRepository.findFollowers(userId));
    }
}
