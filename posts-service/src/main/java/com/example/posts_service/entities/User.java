package com.example.posts_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements AppEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    private Date updatedAt;

    @Column
    private Date deletedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserFollowing> following = new HashSet<>();

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserFollowing> followers = new HashSet<>();

    public String getFullName() {
        return firstname + " " + lastname;
    }

    public List<String> getPostIds() {
        if (this.posts == null) return new ArrayList<>();
        return this.posts.stream().map((Post post) -> {
            return post.getId();
        }).collect(Collectors.toList());
    }

    public List<String> getFollowerIds() {
        if (this.followers == null) return new ArrayList<>();
        return this.followers.stream().map((UserFollowing uf) -> {
            return uf.getFollower().getId();
        }).collect(Collectors.toList());
    }

    public List<String> getFollowingIds() {
        if (this.following == null) return new ArrayList<>();
        return this.following.stream().map((UserFollowing uf) -> {
            return uf.getFollowing().getId();
        }).collect(Collectors.toList());
    }
}
