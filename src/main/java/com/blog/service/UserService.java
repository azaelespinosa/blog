package com.blog.service;

import com.blog.dto.CreateUserDto;
import com.blog.dto.RoleDto;
import com.blog.dto.UpdateUserDto;
import com.blog.dto.UserDto;
import com.blog.model.RoleEntity;
import com.blog.model.UserEntity;

import java.util.List;

public interface UserService {

    UserDto findByUserId(Long id);

    UserEntity findUserByUserName(String userName);

    List<UserDto> findAllUsers();

    UserDto createUser(CreateUserDto dto);

    UserDto updateUser(UpdateUserDto dto);

    void softDeleteUser(Long id);

    RoleDto createRole(RoleDto dto);

    List<RoleEntity> findAllRoles();

}
