package com.example.posts_service.services.Services;

import com.example.posts_service.dtos.exceptions.NotFoundException;
import com.example.posts_service.dtos.exceptions.NotValidException;
import com.example.posts_service.dtos.requests.AcceptFollowReqRequest;
import com.example.posts_service.dtos.requests.CreateUserRequest;
import com.example.posts_service.dtos.requests.SendFollowReqRequest;
import com.example.posts_service.entities.User;
import com.example.posts_service.entities.UserFollowing;
import com.example.posts_service.entities.enums.UserFollowingStatus;
import com.example.posts_service.repositories.UserFollowingRepository;
import com.example.posts_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static java.lang.Character.toUpperCase;

@Service
public class UserManager {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserFollowingRepository userFollowingRepo;

    private static final String FOLLOW_REQUEST_SENT = "Follow request sent successfully.";
    private static final String FOLLOWED_SUCCESS = "Followed Successfully";
    private static final String FOLLOW_REQUEST_APPROVED = "Follow request approved";


    public User createUser(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setUsername(validName(createUserRequest.getUsername()));
        user.setEmail(createUserRequest.getEmail());
        user.setFirstname(validName(createUserRequest.getFirstname()));
        user.setLastname(validName(createUserRequest.getLastname()));
        return userRepo.save(user);
    }


    public String sendFollowRequest(String followerUsername, String followingId) {
        UserFollowing userFollowing = new UserFollowing();
        User followerUser = this.getUserIfExists("username", followerUsername, "User (follower)");
        User followedUser = this.getUserIfExists("id", followingId, "User (followed)");

        if (followerUser.getId().equals(followedUser.getId())) {
            throw new NotValidException("Follower and Followed User can't be same.");
        }

        userFollowing.setFollower(followerUser);
        userFollowing.setFollowing(followedUser);
        userFollowing.setFollowedAt(new Date());
        userFollowing.setStatus(UserFollowingStatus.REQUESTED);

        userFollowingRepo.save(userFollowing);

        return FOLLOW_REQUEST_SENT;
    }


    public String acceptFollowRequest(String followerUsername, String followingId) {
        User followerUser = this.getUserIfExists("username", followerUsername, "User (follower)");

        Optional<UserFollowing> userFollowingOpt = userFollowingRepo.getById(followerUser.getId(), followingId);
        if (userFollowingOpt.isEmpty()) {
            throw new NotFoundException("Follow Request not found.");
        }
        UserFollowing userFollowing = userFollowingOpt.get();
        userFollowing.setStatus(UserFollowingStatus.APPROVED);
        userFollowingRepo.save(userFollowing);

        return FOLLOW_REQUEST_APPROVED;
    }


    private String validName(String input) {
        String regex = "^[a-zA-Z]+$";
        if (!input.matches(regex)) {
            throw new NotValidException("username, firstname & lastname only accept english alphabets, no special characters or spaces.");
        }
        return input;
    }


    private User getUserIfExists(String fieldName, String fieldValue, String userKind) {
        Optional<User> userOpt = userRepo.findByUniqueField(fieldValue);
        if (userOpt.isEmpty()) {
            throw new NotFoundException(userKind + " doesn't exist with " + fieldName + " : " + fieldValue);
        }
        return userOpt.get();
    }
}
