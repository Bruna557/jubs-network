package com.github.Bruna557.restapi.repository;

import com.github.Bruna557.restapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select distinct p from Post p "
            + "left join Follow f on p.userId = f.followedId "
            + "where f.followerId = ?1 or p.userId = ?1 "
            + "order by p.postDate")
    List<Post> findFeedPosts(Long userId);
}
