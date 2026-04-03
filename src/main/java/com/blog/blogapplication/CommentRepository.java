package com.blog.blogapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // get all comments for a specific post
    // SELECT * FROM comments WHERE post_id = ?
    List<Comment> findByPostId(Long postId);
}