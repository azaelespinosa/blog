package com.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateCommentDto implements Serializable{
    private Long id;
    private String commentText;
    private Long postId;
    private Long userId;

}
