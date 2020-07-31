package com.github.Bruna557.restapi.repository;

import com.github.Bruna557.restapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {}
