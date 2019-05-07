package com.blog.service.impl;

import com.blog.aspects.Time;
import com.blog.dto.CommentDto;
import com.blog.common.exceptions.CustomException;
import com.blog.common.services.BaseService;
import com.blog.dto.CreateCommentDto;
import com.blog.dto.UpdateCommentDto;
import com.blog.model.CommentEntity;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class CommentServiceImpl extends BaseService<CommentRepository,CommentEntity> implements CommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Time
    public CommentDto findByCommentId(Long id){
        log.info("Method findByCommentId.");
        try {
            return findById(id, CommentDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Time
    public List<CommentDto> findCommentsByPostId(Long postId){
        log.info("Method findCommentsByPostId.");
        return convertUtils.convert(repository.findByPostIdOrderByModifiedAt(postId) , CommentDto.class);

    }

    @Time
    public List<CommentDto> findCommentsByUserId(Long userId){
        log.info("Method findCommentsByUserId.");
        return convertUtils.convert(repository.findByUserIdOrderByModifiedAt(userId) , CommentDto.class);

    }

    @Time
    @Transactional
    public CommentDto createComment(CreateCommentDto dto){

        log.info("Method createComment.");

        Objects.requireNonNull(dto.getCommentText(),"Please type a comment text.");
        Objects.requireNonNull(dto.getUserId(),"Please type a valid user id.");
        Objects.requireNonNull(dto.getPostId(),"Please type a valid post id.");

        userRepository.findById(dto.getUserId()).orElseThrow(() ->new CustomException("The user doesn't exist."));
        postRepository.findById(dto.getPostId()).orElseThrow(() ->new CustomException("The post doesn't exist."));

        try {
            return create(dto,CommentDto.class);

        }catch(Exception e){
            throw new CustomException("Something happened during creation.");
        }
    }

    @Time
    @Transactional
    public CommentDto updateComment(UpdateCommentDto dto){

        log.info("Method updateComment iniciando.");

        Objects.requireNonNull(dto.getCommentText(),"Please type a comment text.");

        userRepository.findById(dto.getUserId()).orElseThrow(() ->new CustomException("The user doesn't exist."));
        postRepository.findById(dto.getPostId()).orElseThrow(() ->new CustomException("The post doesn't exist."));

        try {
            return update(dto.getId(),dto,CommentDto.class);

        }catch(Exception e){
            throw new CustomException("Something happened during update.");
        }
    }

    @Time
    public void softDeleteComment(Long id){

        log.info("Method softDeleteComment.");

        try {

            delete(id);

        }catch(Exception e){
            throw new CustomException("Something happened during delete :"+id);
        }


    }

}
