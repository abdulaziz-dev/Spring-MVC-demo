package com.uzdev.mvcdemo.repository;

import com.uzdev.mvcdemo.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
    UserEntity findFirstByUsername(String username);
}
