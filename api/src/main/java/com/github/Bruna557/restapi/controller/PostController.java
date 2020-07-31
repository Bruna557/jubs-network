package com.github.Bruna557.restapi.controller;

import com.github.Bruna557.restapi.exception.ResourceNotFoundException;
import com.github.Bruna557.restapi.model.Post;
import com.github.Bruna557.restapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    /**
     * Get all posts list.
     *
     * @return the list
     */
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    /**
     * Gets post by id.
     *
     * @param postId the post id
     * @return the post by id
     * @throws ResourceNotFoundException the resource not found com.github.Bruna557.api.exception
     */
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "id") Long postId)
            throws ResourceNotFoundException {
        Post post =
                postRepository
                        .findById(postId)
                        .orElseThrow(() -> new ResourceNotFoundException("Post not found on :: " + postId));
        return ResponseEntity.ok().body(post);
    }

    /**
     * Create post.
     *
     * @param post the post
     * @return the post
     */
    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postRepository.save(post);
    }

    /**
     * Update post response entity.
     *
     * @param postId the post id
     * @param postDetails the post details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found com.github.Bruna557.api.exception
     */
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable(value = "id") Long postId, @Valid @RequestBody Post postDetails)
            throws ResourceNotFoundException {

        Post post =
                postRepository
                        .findById(postId)
                        .orElseThrow(() -> new ResourceNotFoundException("Post not found on :: " + postId));

        post.setPostText(postDetails.getPostText());
        final Post updatedPost = postRepository.save(post);
        return ResponseEntity.ok(updatedPost);
    }

    /**
     * Delete post map.
     *
     * @param postId the post id
     * @return the map
     * @throws Exception the com.github.Bruna557.api.exception
     */
    @DeleteMapping("/post/{id}")
    public Map<String, Boolean> deletePost(@PathVariable(value = "id") Long postId) throws Exception {
        Post post =
                postRepository
                        .findById(postId)
                        .orElseThrow(() -> new ResourceNotFoundException("Post not found on :: " + postId));

        postRepository.delete(post);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
