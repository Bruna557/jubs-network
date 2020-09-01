package com.github.Bruna557.restapi.service;

import com.github.Bruna557.restapi.exception.ResourceNotFoundException;
import com.github.Bruna557.restapi.exception.UserAlreadyExistException;
import com.github.Bruna557.restapi.exception.WrongPasswordException;
import com.github.Bruna557.restapi.model.User;
import com.github.Bruna557.restapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());

    public User register(User user) throws UserAlreadyExistException {
        if (findById(user.getUserId()).isPresent())
            throw new UserAlreadyExistException("Username taken: " + user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String login(String username, String password) throws WrongPasswordException, ResourceNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            if(passwordEncoder.matches(password, user.get().getPassword())) {
                String token = UUID.randomUUID().toString();
                User usr = user.get();
                usr.setToken(token);
                userRepository.save(usr);
                return token;
            }
            throw new WrongPasswordException("The password is wrong!");
        }
        throw new ResourceNotFoundException("User not found: " + username);
    }

    public User changePassword(Long userId, String password) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            user.get().setPassword(passwordEncoder.encode(password));
            return userRepository.save(user.get());
        }
        throw new ResourceNotFoundException("User not found: id" + userId);
    }

    public Optional<User> findByToken(String token) {
        return userRepository.findByToken(token);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
