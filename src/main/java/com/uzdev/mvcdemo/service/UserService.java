package com.uzdev.mvcdemo.service;

import com.uzdev.mvcdemo.dto.RegisterDto;
import com.uzdev.mvcdemo.models.UserEntity;

public interface UserService {
    void saveUser(RegisterDto registerDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
