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
public class PostDto implements Serializable {

    private Long id;
    private String title;
    private String postText;
    private Long userId;
    private UserDto user;

}
