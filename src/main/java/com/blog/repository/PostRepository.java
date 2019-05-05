package com.blog.repository;

import com.blog.common.repositories.BaseRepository;
import com.blog.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends BaseRepository<PostEntity,Long> {

    List<PostEntity> findByUserIdOrderByModifiedAt(Long userId);


}
