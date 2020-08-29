package com.github.Bruna557.restapi.repository;

import com.github.Bruna557.restapi.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query(value = "SELECT f FROM Follow f WHERE f.followerId = ?1")
    List<Follow> findFollowed(Long followerId);
}
