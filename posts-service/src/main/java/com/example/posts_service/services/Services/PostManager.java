package com.example.posts_service.services.Services;

import com.example.posts_service.entities.Comment;
import com.example.posts_service.entities.Post;
import com.example.posts_service.repositories.CommentRepository;
import com.example.posts_service.repositories.LikeRepository;
import com.example.posts_service.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostManager {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private LikeRepository likeRepo;

    @Autowired
    private CommentRepository commentRepo;

    public void createPost() {

    }

    public void createCommentOnPost() {

    }

    public void createLikeOnPost() {

    }

    public void getLikesCountOnPost() {

    }

    public void getCommentsCountOnPost() {

    }

    public void getLikesOnPost() {

    }

    public void getCommentsOnPost() {

    }
}
