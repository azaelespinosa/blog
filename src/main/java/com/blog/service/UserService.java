package com.blog.service;

import com.blog.dto.UserDto;
import com.blog.model.UserEntity;

public interface UserService {

    UserDto findByUserId(Long id);

    UserEntity findUserByUserName(String userName);

    UserDto createUser(UserDto dto);

    UserDto updateUser(UserDto dto);

    void softDeleteUser(Long id);
}
