package com.blog.service.impl;

import com.blog.common.exceptions.CustomException;
import com.blog.common.services.BaseService;
import com.blog.dto.CreateUserDto;
import com.blog.dto.RoleDto;
import com.blog.dto.UpdateUserDto;
import com.blog.dto.UserDto;
import com.blog.model.RoleEntity;
import com.blog.model.UserEntity;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.service.RoleRepository;
import com.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl extends BaseService<UserRepository,UserEntity> implements UserService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserDto findByUserId(Long id){
        log.info("Method findByUserId.");
        try {
            return findById(id, UserDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private UserEntity findUserByUserNameAndEmail(String userName, String email){
        log.info("Method findPostsByUserId.");
        return repository.findByUsernameAndEmail(userName,email);

    }

    public UserEntity findUserByUserName(String userName){
        log.info("Method findUserByUserName.");
        return repository.findByUsername(userName);
    }


    @Transactional
    public UserDto createUser(CreateUserDto dto){

        log.info("Method createUser.");

        Objects.requireNonNull(dto.getUsername(), "Please type a username.");
        Objects.requireNonNull(dto.getEmail(), "Please type an email.");
        Objects.requireNonNull(dto.getRoleId(), "Please type a roleId.");

        UserEntity user = repository.findByUsernameAndEmail(dto.getUsername(),dto.getEmail());

        if(Optional.ofNullable(user ).map(UserEntity::getId).map(l -> l != null).orElse(false)){
            throw new CustomException("The user already exist.");
        }

        roleRepository.findById(dto.getRoleId()).orElseThrow(() ->new CustomException("The role doesn't exist."));

        try {

            return create(dto,UserDto.class);

        }catch(Exception e){
            throw new CustomException("Something happened during creation.");
        }
    }

    @Transactional
    public UserDto updateUser(UpdateUserDto dto) {

        log.info("Method updateUser.");

        Objects.requireNonNull(dto.getId(), "Please type a valid id.");
        Objects.requireNonNull(dto.getUsername(), "Please type a username.");
        Objects.requireNonNull(dto.getEmail(), "Please type an email.");
        Objects.requireNonNull(dto.getRoleId(), "Please type a roleId.");

        roleRepository.findById(dto.getRoleId()).orElseThrow(() ->new CustomException("The role doesn't exist."));

        try {
            return update(dto.getId(), dto, UserDto.class);

        } catch (Exception e) {
            throw new CustomException("Something happened during update.");

        }

    }

    public void softDeleteUser(Long id){

        log.info("Method softDeletePost.");

        UserEntity user = repository.findById(id).orElseThrow(() ->new CustomException("The user doesn't exist."));

        if(Optional.ofNullable(postRepository.findByUserIdOrderByModifiedAt(user.getId())).map(l -> !l.isEmpty()).orElse(false)) {
            throw new CustomException("You can't delete these user, the user have active posts.");
        }

        if(Optional.ofNullable(commentRepository.findByUserIdOrderByModifiedAt(user.getId())).map(l -> !l.isEmpty()).orElse(false)) {
            throw new CustomException("You can't delete these user, the user have active comments.");
        }

        try {

            user.setSoftDelete(true);
            repository.save(user);

        }catch(Exception e){
            throw new CustomException("Something happened during delete :"+id);
        }

    }

    @Transactional
    public RoleDto createRole(RoleDto dto){

        log.info("Method createRole.");

        Objects.requireNonNull(dto.getName(), "Please type a role name.");

        if(Optional.ofNullable( roleRepository.findByName(dto.getName())).map(RoleEntity::getId).map(l -> l > 0).orElse(false)){
            throw new CustomException("The role already exist.");
        }

        try {

            return convertUtils.convert(roleRepository.save(RoleEntity.builder().name(dto.getName()).build()),RoleDto.class);

        }catch(Exception e){
            throw new CustomException("Something happened during creation.");
        }
    }

    public List<RoleEntity> findAllRoles(){
        return roleRepository.findAll();
    }

}
