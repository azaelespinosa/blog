package com.blog.dto;

import com.blog.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto implements Serializable {

    private Long id;
    private String username;
    private String email;
    private Long roleId;
    private RoleEntity role;

}
