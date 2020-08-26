package com.github.Bruna557.restapi.repository;

import com.github.Bruna557.restapi.model.Post;
import com.github.Bruna557.restapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT p FROM Post p WHERE p.userId = ?1")
    List<Post> findUserPosts(Long userId);
}
