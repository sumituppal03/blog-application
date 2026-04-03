package com.blog.blogapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
// JpaRepository<Post, Long>
// Post = which entity
// Long = type of the ID field
public interface PostRepository extends JpaRepository<Post, Long> {

    // Spring reads this method name and writes SQL automatically:
    // SELECT * FROM posts WHERE title LIKE %keyword%
    List<Post> findByTitleContaining(String keyword);
}
