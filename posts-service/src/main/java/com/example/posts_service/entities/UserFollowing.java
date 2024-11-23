package com.example.posts_service.entities;

import com.example.posts_service.entities.enums.UserFollowingStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_following")
public class UserFollowing implements AppEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false, updatable = false)
    @JsonIgnoreProperties({"posts", "followers", "following", "postId", "createdAt", "updatedAt", "deletedAt"})
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false, updatable = false)
    @JsonIgnoreProperties({"posts", "followers", "following", "postId", "createdAt", "updatedAt", "deletedAt"})
    private User following;

    @Column
    private Date followedAt;

    @Column
    private Date approvedAt;

    @Column
    private UserFollowingStatus status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;
}
