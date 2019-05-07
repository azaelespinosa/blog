package com.blog.controller;

import com.blog.dto.CreateUserDto;
import com.blog.dto.RoleDto;
import com.blog.dto.UpdateUserDto;
import com.blog.dto.UserDto;
import com.blog.model.RoleEntity;
import com.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "User Controller")
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find user by id.")
    public UserDto findByUserId(@RequestParam Long id){
        return userService.findByUserId(id);
    }

    @PostMapping
    @ApiOperation(value = "Create user.")
    public UserDto createUser(@RequestBody CreateUserDto dto){
        return  userService.createUser(dto);
    }

    @PutMapping
    @ApiOperation(value = "Update user.")
    public UserDto updateUser(@RequestBody UpdateUserDto dto){
        return  userService.updateUser(dto);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete user.")
    public void softDeleteUser(@RequestParam Long id){
        userService.softDeleteUser(id);
    }

}
