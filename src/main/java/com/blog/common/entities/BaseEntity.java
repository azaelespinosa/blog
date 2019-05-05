package com.blog.common.entities;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {

    @Column(name = "SOFT_DELETE" ,nullable = false)
    private boolean softDelete;

    @Column(name = "MODIFIED_AT", nullable = false)
    private Timestamp modifiedAt;

    @Column(name = "CREATED_AT",nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "UUID",nullable = false, updatable = false)
    private String uuid;

    protected BaseEntity() {

    }

    @PrePersist
    public void setAuditInfo() {
        Calendar current = Calendar.getInstance();
        this.setCreatedAt(new Timestamp(current.getTimeInMillis()));
        this.setSoftDelete(false);
        this.setModifiedAt(new Timestamp(current.getTimeInMillis()));
        if (StringUtils.isEmpty(uuid)) this.setUuid(UUID.randomUUID().toString());
    }

    @PreUpdate
    public void setModifyInfo() {
        Calendar current = Calendar.getInstance();
        this.setModifiedAt(new Timestamp(current.getTimeInMillis()));
    }
}