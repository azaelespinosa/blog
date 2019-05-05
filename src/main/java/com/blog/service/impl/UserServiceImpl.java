package com.blog.service.impl;

import com.blog.common.exceptions.CustomException;
import com.blog.common.services.BaseService;
import com.blog.dto.UserDto;
import com.blog.model.UserEntity;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl extends BaseService<UserRepository,UserEntity> implements UserService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

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
    public UserDto createUser(UserDto dto){

        log.info("Method createUser.");

        Objects.requireNonNull(dto.getUsername(), "Please type a username.");
        Objects.requireNonNull(dto.getEmail(), "Please type an email.");

        if(Optional.ofNullable( findUserByUserNameAndEmail(dto.getUsername(),dto.getEmail())).map(UserEntity::getId).map(l -> l > 0).orElse(false)){
            new CustomException("The user already exist.");
        }

        try {
            return create(dto,UserDto.class);

        }catch(Exception e){
            throw new CustomException("Something happened during creation.");
        }
    }

    @Transactional
    public UserDto updateUser(UserDto dto) {

        log.info("Method updateUser.");

        Objects.requireNonNull(dto.getUsername(), "Please type a username.");
        Objects.requireNonNull(dto.getEmail(), "Please type an email.");

        if (Optional.ofNullable(findUserByUserNameAndEmail(dto.getUsername(), dto.getEmail())).map(UserEntity::getId).map(l -> l > 0).orElse(false)) {
            new CustomException("The user already exist.");
        }

        try {
            return update(dto.getId(), dto, UserDto.class);

        } catch (Exception e) {
            throw new CustomException("Something happened during update.");

        }

    }

        public void softDeleteUser(Long id){

        log.info("Method softDeletePost.");

        UserEntity user = Optional.ofNullable( repository.getOne(id)).orElseThrow(() -> new CustomException("The user doesn't exist."));

        if(Optional.ofNullable(postRepository.findByUserIdOrderByModifiedAt(id)).map(l -> !l.isEmpty()).orElse(false)) {
            new CustomException("You can't delete these user, the user have active posts.");
        }

        if(Optional.ofNullable(commentRepository.findByUserIdOrderByModifiedAt(id)).map(l -> !l.isEmpty()).orElse(false)) {
            new CustomException("You can't delete these user, the user have active comments.");
        }

        try {

            user.setSoftDelete(true);
            repository.save(user);

        }catch(Exception e){
            throw new CustomException("Something happened during delete :"+id);
        }


    }


}
