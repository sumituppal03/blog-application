package com.blog.blogapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;   // talks to Service, not Repository

    // GET /api/posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // GET /api/posts/1
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);   // returns 200 OK with post
    }

    // POST /api/posts
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post created = postService.createPost(post);
        return ResponseEntity.status(201).body(created); // 201 = Created
    }

    // PUT /api/posts/1
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long id,
            @RequestBody Post post) {
        Post updated = postService.updatePost(id, post);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/posts/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

    // GET /api/posts/search?keyword=spring
    @GetMapping("/search")
    public List<Post> searchPosts(@RequestParam String keyword) {
        return postService.searchPosts(keyword);
    }
}