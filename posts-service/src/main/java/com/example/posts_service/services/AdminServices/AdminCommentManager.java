package com.example.posts_service.services.AdminServices;

import com.example.posts_service.dtos.exceptions.NotFoundException;
import com.example.posts_service.dtos.exceptions.NotValidException;
import com.example.posts_service.dtos.requests.admin_requests.AdminCreateCommentRequest;
import com.example.posts_service.entities.*;
import com.example.posts_service.repositories.CommentRepository;
import com.example.posts_service.repositories.PostRepository;
import com.example.posts_service.repositories.UserRepository;
import com.example.posts_service.services.AdminEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminCommentManager implements AdminEntityManager {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;


    @Override
    public AppEntity create(Map<String, Object> reqBody) {
        AdminCreateCommentRequest createCommentRequest = new AdminCreateCommentRequest(reqBody);
        Comment comment = new Comment();

        comment.setContent(createCommentRequest.getContent());

        Optional<User> user = userRepo.findById(createCommentRequest.getUserId());
        if (user.isEmpty()) {
            throw new NotFoundException("User not found with Id: " + createCommentRequest.getUserId());
        }
        comment.setUser(user.get());

        Optional<Post> post = postRepo.findById(createCommentRequest.getPostId());
        if (post.isEmpty()) {
            throw new NotFoundException("Post not found with Id: " + createCommentRequest.getPostId());
        }
        comment.setPost(post.get());

        return commentRepo.save(comment);
    }


    @Override
    public AppEntity update(Map<String, Object> reqBody) {
        return null;
    }


    @Override
    public AppEntity delete(Map<String, Object> reqBody) {
        return null;
    }


    @Override
    public List<AppEntity> getAll() {
        List<AppEntity> res = new ArrayList<>(commentRepo.findAll());
        return res;
    }


    @Override
    public AppEntity getOne(String id) {
        Optional<Comment> x = commentRepo.findById(id);
        if (x.isEmpty()) {
            throw new NotFoundException("Comment not found with Id: " + id);
        }
        return x.get();
    }
}
