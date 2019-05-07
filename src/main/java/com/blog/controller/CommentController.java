package com.blog.controller;

import com.blog.dto.CommentDto;
import com.blog.dto.CreateCommentDto;
import com.blog.dto.UpdateCommentDto;
import com.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "Comment Controller")
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find comment by id.")
    public CommentDto findCommentById(@RequestParam Long id){
        return commentService.findByCommentId(id);
    }

    @GetMapping(value = "/all/{postId}")
    @ApiOperation(value = "Find comment by post id.")
    public List<CommentDto> findCommentsByPostId(@RequestParam Long postId){
        return commentService.findCommentsByPostId(postId);
    }

    @GetMapping(value = "/all/user/{userId}")
    @ApiOperation(value = "Find comment by user id.")
    public List<CommentDto> findCommentsByUserId(@RequestParam Long userId){
        return commentService.findCommentsByUserId(userId);
    }

    @PostMapping
    @ApiOperation(value = "Create comment.")
    public CommentDto createComment(@RequestBody CreateCommentDto dto){
        return  commentService.createComment(dto);
    }

    @PutMapping
    @ApiOperation(value = "Update comment.")
    public CommentDto updateComment(@RequestBody UpdateCommentDto dto){
        return  commentService.updateComment(dto);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete comment.")
    public void deleteComment(@RequestParam Long id){
         commentService.softDeleteComment(id);
    }

}
