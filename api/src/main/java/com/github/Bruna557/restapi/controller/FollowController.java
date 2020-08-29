package com.github.Bruna557.restapi.controller;

import com.github.Bruna557.restapi.repository.FollowRepository;
import com.github.Bruna557.restapi.model.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("user/{userId}/follow")
    ResponseEntity<List<Follow>> getFollowed(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(followRepository.findFollowed(userId));
    }
}
