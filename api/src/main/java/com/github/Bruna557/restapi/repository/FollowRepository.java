package com.github.Bruna557.restapi.repository;

import com.github.Bruna557.restapi.model.Follow;
import com.github.Bruna557.restapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query(value = "select u from User u left join Follow f " +
            "on u.userId = f.followedId " +
            "where f.followerId = ?1")
    List<User> findFollowed(Long userId);

    @Query(value = "select u from User u left join Follow f " +
            "on u.userId = f.followerId " +
            "where f.followedId = ?1")
    List<User> findFollowers(Long userId);

    Optional<Follow> findByFollowerIdAndFollowedId(Long followerId, Long followedId);
}
