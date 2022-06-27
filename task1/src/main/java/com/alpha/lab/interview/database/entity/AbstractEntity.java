package com.alpha.lab.interview.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {

    @Column(name = "DELETED")
    @ColumnDefault("false")
    private boolean deleted;

    @CreatedDate
    @Column(name = "CREATED_TS", insertable = false, updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "UPDATED_TS")
    private LocalDateTime updated;

}
