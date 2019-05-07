package com.blog.service.impl;

import com.blog.common.exceptions.CustomException;
import com.blog.common.services.BaseService;
import com.blog.dto.PostDto;
import com.blog.model.PostEntity;
import com.blog.model.UserEntity;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.service.PostService;
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
public class PostServiceImpl extends BaseService<PostRepository,PostEntity> implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    public PostDto findByPostId(Long id){
        log.info("Method findByPostId.");
        try {
            return findById(id, PostDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<PostDto> findPostsByUserId(Long userId){
        log.info("Method findPostsByUserId.");
        return convertUtils.convert(repository.findByUserIdOrderByModifiedAt(userId) , PostDto.class);

    }

    @Transactional
    public PostDto createPost(PostDto dto){

        log.info("Method createPost.");

        Objects.requireNonNull(dto.getTitle(),"Please type a title.");
        Objects.requireNonNull(dto.getPostText(),"Please type a post text.");
        Objects.requireNonNull(dto.getUserId(),"Please type a valid user id.");

        userRepository.findById(dto.getUserId()).orElseThrow(() ->new CustomException("The user doesn't exist."));

        try {
            return create(dto,PostDto.class);

        }catch(Exception e){
            throw new CustomException("Something happened during creation.");
        }
    }

    @Transactional
    public PostDto updatePost(PostDto dto){

        log.info("Method updatePost.");

        Objects.requireNonNull(dto.getId(),"Please type a valid id.");
        Objects.requireNonNull(dto.getTitle(),"Please type a title.");
        Objects.requireNonNull(dto.getPostText(),"Please type a post text.");
        Objects.requireNonNull(dto.getUserId(),"Please type a valid user id.");

        userRepository.findById(dto.getUserId()).orElseThrow(() ->new CustomException("The user doesn't exist."));

        try {
            return update(dto.getId(),dto,PostDto.class);

        }catch(Exception e){
            throw new CustomException("Something happened during creation.");
        }
    }

    public void softDeletePost(Long id){

        log.info("Method softDeletePost.");

        if(Optional.ofNullable(commentRepository.findByPostIdOrderByModifiedAt(id)).map(l -> !l.isEmpty()).orElse(false)) {
            throw new CustomException("You can't delete these post, the user have active comments.");
        }

        try {

            delete(id);

        }catch(Exception e){
            throw new CustomException("Something happened during delete :"+id);
        }


    }

}
