package com.blog.repository;

import com.blog.common.repositories.BaseRepository;
import com.blog.model.UserEntity;

public interface UserRepository extends BaseRepository<UserEntity,Long> {

    UserEntity findByUsernameAndEmail(String userName, String email);

    UserEntity findByUsername(String name);
}
