package com.blog.blogapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service    // tells Spring: this class is a service layer component
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // get all posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // get one post by id
    public Post getPostById(Long id) {
        // findById returns Optional — orElseThrow throws error if not found
        return postRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Post not found with id: " + id));
    }

    // create new post
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // update existing post
    public Post updatePost(Long id, Post updatedPost) {
        Post existing = getPostById(id);   // reuse our method above
        existing.setTitle(updatedPost.getTitle());
        existing.setContent(updatedPost.getContent());
        return postRepository.save(existing);
    }

    // delete post — comments auto delete because of CascadeType.ALL
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // search posts by title keyword
    public List<Post> searchPosts(String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }
}
