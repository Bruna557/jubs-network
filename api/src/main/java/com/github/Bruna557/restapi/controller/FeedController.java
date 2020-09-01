package com.github.Bruna557.restapi.controller;

import com.github.Bruna557.restapi.exception.ResourceNotFoundException;
import com.github.Bruna557.restapi.model.Post;
import com.github.Bruna557.restapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/user/{userId}/feed")
    public ResponseEntity<List<Post>> getPosts(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(postRepository.findFeedPosts(userId));
    }

    @PutMapping("/user/{userId}/feed")
    public ResponseEntity<Post> likePost(@RequestParam Long postId) throws ResourceNotFoundException {
        Post post =
                postRepository
                        .findById(postId)
                        .orElseThrow(() -> new ResourceNotFoundException("Post not found on :: " + postId));
        post.setPostVotes(post.getPostVotes()+1);
        final Post updatedPost = postRepository.save(post);
        return ResponseEntity.ok(updatedPost);
    }
}
