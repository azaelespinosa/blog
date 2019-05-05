package com.blog.controller;

import com.blog.dto.PostDto;
import com.blog.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "Post")
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find post by id.")
    public PostDto findCommentById(@RequestParam Long id){
        return postService.findByPostId(id);
    }


    @GetMapping(value = "/all/user/{userId}")
    @ApiOperation(value = "Find post by user id.")
    public List<PostDto> findPostsByUserId(@RequestParam Long userId){
        return postService.findPostsByUserId(userId);
    }

    @PostMapping
    @ApiOperation(value = "Create post.")
    public PostDto createPost(@RequestBody PostDto dto){
        return  postService.createPost(dto);
    }

    @PutMapping
    @ApiOperation(value = "Update post.")
    public PostDto updatePost(@RequestBody PostDto dto){
        return  postService.updatePost(dto);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete post.")
    public void deletePost(@RequestParam Long id){
        postService.softDeletePost(id);
    }


}
