package com.blog.repository;

import com.blog.common.repositories.BaseRepository;
import com.blog.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends BaseRepository<CommentEntity,Long> {

    List<CommentEntity> findByPostIdOrderByModifiedAt(Long postId);

    List<CommentEntity> findByUserIdOrderByModifiedAt(Long userId);

}
