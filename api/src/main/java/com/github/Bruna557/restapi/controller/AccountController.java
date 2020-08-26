package com.github.Bruna557.restapi.controller;

import com.github.Bruna557.restapi.exception.ResourceNotFoundException;
import com.github.Bruna557.restapi.exception.UserAlreadyExistException;
import com.github.Bruna557.restapi.exception.WrongPasswordException;
import com.github.Bruna557.restapi.model.User;
import com.github.Bruna557.restapi.repository.UserRepository;
import com.github.Bruna557.restapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AccountController {
    @Autowired
    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws UserAlreadyExistException {
        User usr = userService.register(user);
        return ResponseEntity.ok(usr);
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) throws ResourceNotFoundException, WrongPasswordException {
        return userService.login(username, password);
    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<User> logout(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        user.setToken(null);
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
}
