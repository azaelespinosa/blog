package com.blog.service;

import com.blog.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto findByPostId(Long id);

    List<PostDto> findPostsByUserId(Long userId);

    PostDto createPost(PostDto dto);

    PostDto updatePost(PostDto dto);

    void softDeletePost(Long id);

}
