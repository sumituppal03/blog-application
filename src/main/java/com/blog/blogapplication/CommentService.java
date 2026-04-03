package com.blog.blogapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // get all comments for a post
    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // add a comment to a post
    public Comment addComment(Long postId, Comment comment) {
        // first find the post this comment belongs to
        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                    new RuntimeException("Post not found with id: " + postId));

        // link the comment to the post
        comment.setPost(post);

        // save comment to database
        return commentRepository.save(comment);
    }

    // delete a comment
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
