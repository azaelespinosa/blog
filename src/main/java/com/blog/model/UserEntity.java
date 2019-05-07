package com.blog.model;

import com.blog.common.entities.BaseEntity;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Table(name = "USER")
@Where(clause = "SOFT_DELETE = false")
public class UserEntity extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "ROLE_ID", nullable = false)
    private Long roleId;

    @OneToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private List<CommentEntity> comments;

    @OneToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "POST_ID", insertable = false, updatable = false)
    private List<PostEntity> posts;

    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
    private RoleEntity role;


}
