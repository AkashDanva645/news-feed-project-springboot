package com.example.posts_service.repositories;

import com.example.posts_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.id=:value OR u.username=:value OR u.email=:value")
    Optional<User> findByUniqueField(@Param("value") String value);
}
