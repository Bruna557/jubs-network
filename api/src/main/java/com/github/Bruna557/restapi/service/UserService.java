package com.github.Bruna557.restapi.service;

import com.github.Bruna557.restapi.model.User;
import com.github.Bruna557.restapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String login(String username, String password) {
        Optional<User> user = userRepository.login(username,password);
        System.out.println("Login -> user: " + username);
        if(user.isPresent()){
            String token = UUID.randomUUID().toString();
            User usr = user.get();
            usr.setToken(token);
            userRepository.save(usr);
            System.out.println("Token: " + token);
            return token;
        }
        return StringUtils.EMPTY;
    }

    public Optional<User> findByToken(String token) {
        return userRepository.findByToken(token);
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
}
