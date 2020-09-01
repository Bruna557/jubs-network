package com.github.Bruna557.restapi.controller;

import com.github.Bruna557.restapi.exception.ResourceNotFoundException;
import com.github.Bruna557.restapi.model.Post;
import com.github.Bruna557.restapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @PreAuthorize("#userId == authentication.principal.userId")
    @PostMapping("/user/{userId}/post")
    public Post createPost(@PathVariable(value = "userId") Long userId, @RequestBody String postText) {
        Post post = new Post(postText, userId);
        return postRepository.save(post);
    }

    @PreAuthorize("#userId == authentication.principal.userId")
    @PutMapping("/user/{userId}/post")
    public ResponseEntity<Post> updatePost(
            @RequestParam Long postId, @RequestBody String postText)
            throws ResourceNotFoundException {

        Post post =
                postRepository
                        .findById(postId)
                        .orElseThrow(() -> new ResourceNotFoundException("Post not found on :: " + postId));

        post.setPostText(postText);
        final Post updatedPost = postRepository.save(post);
        return ResponseEntity.ok(updatedPost);
    }

    @PreAuthorize("#userId == authentication.principal.userId")
    @DeleteMapping("/user/{userId}/post")
    public ResponseEntity<HttpStatus> deletePost(
            @PathVariable(value = "userId") Long userId, @RequestParam Long postId) throws Exception {
        Post post =
                postRepository
                        .findById(postId)
                        .orElseThrow(() -> new ResourceNotFoundException("Post not found on :: " + postId));

        if(post.getUserId() != userId)
            return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
        postRepository.delete(post);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
