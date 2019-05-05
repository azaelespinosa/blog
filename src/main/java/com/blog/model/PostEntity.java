package com.blog.model;

import com.blog.common.entities.BaseEntity;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Table(name = "POST")
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "POST_TEXT", nullable = false)
    private String postText;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @OneToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "COMMENT_ID", insertable = false, updatable = false)
    private List<CommentEntity> comments;

    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private UserEntity user;



}
