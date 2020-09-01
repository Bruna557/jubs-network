package com.github.Bruna557.restapi.controller;

import com.github.Bruna557.restapi.exception.ResourceNotFoundException;
import com.github.Bruna557.restapi.model.User;
import com.github.Bruna557.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<Long> getUserId(@RequestParam String username)
            throws ResourceNotFoundException {
        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + username));
        return ResponseEntity.ok().body(user.getUserId());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PreAuthorize("#userId == authentication.principal.userId")
    @GetMapping("/user/{id}/search")
    public ResponseEntity<List<User>> searchUser(
            @PathVariable(value = "id") Long userId, @RequestParam String username) {
        return ResponseEntity.ok().body(userRepository.search(username, userId));
    }

    @PreAuthorize("#userId == authentication.principal.userId")
    @PutMapping("/user/{id}/change-picture")
    public ResponseEntity<User> updateImage(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody String newPicture)
            throws ResourceNotFoundException {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        user.setUserPicture(newPicture);
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("#userId == authentication.principal.userId")
    @PutMapping("/user/{id}/change-description")
    public ResponseEntity<User> updateDescription(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody String newDescription)
            throws ResourceNotFoundException {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        user.setUserDescription(newDescription);
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("#userId == authentication.principal.userId")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        userRepository.delete(user);
        return ResponseEntity.ok(true);
    }
}
