package com.blog.service;

import com.blog.dto.CommentDto;
import com.blog.dto.CreateCommentDto;
import com.blog.dto.UpdateCommentDto;

import java.util.List;

public interface CommentService {

    CommentDto findByCommentId(Long id);

    List<CommentDto> findCommentsByPostId(Long postId);

    List<CommentDto> findCommentsByUserId(Long postId);

    CommentDto createComment(CreateCommentDto dto);

    CommentDto updateComment(UpdateCommentDto dto);

    void softDeleteComment(Long id);

}
