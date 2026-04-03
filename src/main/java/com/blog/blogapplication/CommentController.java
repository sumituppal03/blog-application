package com.blog.blogapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // GET /api/posts/1/comments → get all comments for post 1
    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    // POST /api/posts/1/comments → add comment to post 1
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> addComment(
            @PathVariable Long postId,
            @RequestBody Comment comment) {
        Comment created = commentService.addComment(postId, comment);
        return ResponseEntity.status(201).body(created);
    }

    // DELETE /api/comments/1 → delete comment with id 1
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
