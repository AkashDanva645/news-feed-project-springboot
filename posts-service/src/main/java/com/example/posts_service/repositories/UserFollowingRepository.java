package com.example.posts_service.repositories;

import com.example.posts_service.entities.User;
import com.example.posts_service.entities.UserFollowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserFollowingRepository extends JpaRepository<UserFollowing, String> {

    @Query("SELECT uf FROM UserFollowing uf WHERE uf.following.id=:followingId AND uf.follower.id=:followerId")
    Optional<UserFollowing> getById(@Param("followerId") String followerId, @Param("followingId") String followingId);
}
