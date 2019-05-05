package com.blog.service.impl;

import com.blog.dto.CommentDto;
import com.blog.common.exceptions.CustomException;
import com.blog.common.services.BaseService;
import com.blog.model.CommentEntity;
import com.blog.repository.CommentRepository;
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

    public CommentDto findByCommentId(Long id){
        log.info("Method findByCommentId.");
        try {
            return findById(id, CommentDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<CommentDto> findCommentsByPostId(Long postId){
        log.info("Method findCommentsByPostId.");
        return convertUtils.convert(repository.findByPostIdOrderByModifiedAt(postId) , CommentDto.class);

    }

    public List<CommentDto> findCommentsByUserId(Long userId){
        log.info("Method findCommentsByUserId.");
        return convertUtils.convert(repository.findByUserIdOrderByModifiedAt(userId) , CommentDto.class);

    }

    @Transactional
    public CommentDto createComment(CommentDto dto){

        log.info("Method createComment.");

        Objects.requireNonNull(dto.getCommentText(),"Please type a comment text.");
        Objects.requireNonNull(dto.getUserId(),"Please type a valid user id.");

        Optional.ofNullable( userRepository.getOne(dto.getId())).orElseThrow(() -> new CustomException("The user doesn't exist."));

        try {
            return create(dto,CommentDto.class);

        }catch(Exception e){
            throw new CustomException("Something happened during creation.");
        }
    }

    @Transactional
    public CommentDto updateComment(CommentDto dto){

        log.info("Method updateComment iniciando.");

        if (Optional.ofNullable(dto).map(CommentDto::getCommentText).map(t -> t == null).orElse(true)) {
            throw new RuntimeException("Please type a comment.");
        }

        try {
            return update(dto.getId(),dto,CommentDto.class);

        }catch(Exception e){
            throw new CustomException("Something happened during update.");
        }
    }

    public void softDeleteComment(Long id){

        log.info("Method softDeleteComment.");

        try {

            delete(id);

        }catch(Exception e){
            throw new CustomException("Something happened during delete :"+id);
        }


    }

}
