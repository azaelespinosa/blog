package com.blog.model;

import com.blog.common.entities.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Table(name = "COMMENT")
@Where(clause = "SOFT_DELETE = false")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "COMMENT_TEXT", nullable = false)
    private String commentText;

    @Column(name = "POST_ID", nullable = false)
    private Long postId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;





}
