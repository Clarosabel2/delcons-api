package com.delcons.features.user.repository;

import com.delcons.features.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

    boolean existsUserByUsernameOrEmail(String username, String email);
}
