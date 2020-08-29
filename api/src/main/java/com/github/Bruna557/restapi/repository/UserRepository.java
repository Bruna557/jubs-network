package com.github.Bruna557.restapi.repository;

import com.github.Bruna557.restapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u WHERE u.username = ?1 and u.password = ?2")
    Optional<User> login(String username, String password);

    @Query(value = "SELECT u FROM User u WHERE u.username LIKE %?1% and u.userId != ?2")
    List<User> search(String username, Long userId);

    Optional<User> findByToken(String token);

    Optional<User> findByUsername(String username);
}