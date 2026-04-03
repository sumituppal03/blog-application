package com.blog.blogapplication;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity                    // this class = database table
@Data                      // Lombok: auto generates getters/setters
@Table(name = "posts")     // table name in MySQL will be "posts"
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // auto-incremented primary key

    @Column(nullable = false)          // title cannot be empty in DB
    private String title;

    @Column(nullable = false, length = 5000)  // content up to 5000 chars
    private String content;

    // ONE post has MANY comments
    // mappedBy = "post" means Comment class has a field called "post"
    // cascade = ALL means if Post deleted, all its Comments also delete
    // fetch = LAZY means comments are loaded only when you ask for them
    @OneToMany(mappedBy = "post",
               cascade = CascadeType.ALL,
               fetch = FetchType.LAZY)
    private List<Comment> comments;
}
