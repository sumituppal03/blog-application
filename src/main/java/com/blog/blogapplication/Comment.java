package com.blog.blogapplication;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String body;         // the comment text

    private String email;        // who commented

    // MANY comments belong to ONE post
    // @JoinColumn creates a foreign key column "post_id" in comments table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
