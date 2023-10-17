package com.jygoh.SocialNest.repository;


import com.jygoh.SocialNest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
