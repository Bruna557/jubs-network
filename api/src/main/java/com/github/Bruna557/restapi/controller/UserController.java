package com.github.Bruna557.restapi.controller;

import com.github.Bruna557.restapi.exception.ResourceNotFoundException;
import com.github.Bruna557.restapi.model.User;
import com.github.Bruna557.restapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<Long> getUserId(@RequestParam String username)
            throws ResourceNotFoundException {
        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + username));
        return ResponseEntity.ok().body(user.getUserId());
    }

    @PreAuthorize("#userId == authentication.principal.userId")
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/search")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @PreAuthorize("#userId == authentication.principal.userId")
    @PutMapping("/user/{id}/change-password")
    public ResponseEntity<User> updatePassword(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody String newPassword)
            throws ResourceNotFoundException {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        user.setPassword(newPassword);
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
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
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
