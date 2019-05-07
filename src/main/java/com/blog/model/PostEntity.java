package com.blog.model;

import com.blog.common.entities.BaseEntity;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Table(name = "POST")
@Where(clause = "SOFT_DELETE = false")
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
    @JoinColumn(name = "POST_ID",referencedColumnName = "POST_ID",insertable = false, updatable = false)
    private List<CommentEntity> comments;

    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private UserEntity user;



}
