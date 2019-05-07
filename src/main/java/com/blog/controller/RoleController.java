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
@Api(description = "Role Controller")
@RequestMapping("role")
public class RoleController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/role")
    @ApiOperation(value = "Create role.")
    public RoleDto createRole(@RequestBody RoleDto dto){
        return  userService.createRole(dto);
    }

    @GetMapping(value = "/roles")
    @ApiOperation(value = "Find all roles.")
    public List<RoleEntity> findAllRoles(){
        return userService.findAllRoles();
    }

}
