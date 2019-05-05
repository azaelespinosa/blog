package com.blog.service;

import com.blog.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto findByCommentId(Long id);

    List<CommentDto> findCommentsByPostId(Long postId);

    List<CommentDto> findCommentsByUserId(Long postId);

    CommentDto createComment(CommentDto dto);

    CommentDto updateComment(CommentDto dto);

    void softDeleteComment(Long id);

}
