package com.blog.service;

import com.blog.dto.RoleDto;
import com.blog.dto.UserDto;
import com.blog.model.RoleEntity;
import com.blog.model.UserEntity;

import java.util.List;

public interface UserService {

    UserDto findByUserId(Long id);

    UserEntity findUserByUserName(String userName);

    UserDto createUser(UserDto dto);

    UserDto updateUser(UserDto dto);

    void softDeleteUser(Long id);

    RoleDto createRole(RoleDto dto);

    List<RoleEntity> findAllRoles();

}
