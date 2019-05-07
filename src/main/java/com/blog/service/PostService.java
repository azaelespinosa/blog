package com.blog.service;

import com.blog.dto.CreatePostDto;
import com.blog.dto.PostDto;
import com.blog.dto.UpdatePostDto;

import java.util.List;

public interface PostService {

    PostDto findByPostId(Long id);

    List<PostDto> findPostsByUserId(Long userId);

    PostDto createPost(CreatePostDto dto);

    PostDto updatePost(UpdatePostDto dto);

    void softDeletePost(Long id);

}
