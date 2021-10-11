package com.amf.portal.board.domain.post.model;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스가 BaseEntity를 상속 받는 경우 필드들(createdDate, modifiedDate)을 컬럼으로 인식하게 한다.
@EntityListeners(AuditingEntityListener.class) // BaseEntity에 Auditing 기능을 포함시킨다.
public abstract class BaseEntity {

    @CreatedDate // Entity가 생성되어 저장될 때 시간을 자동으로 저장한다.
    private LocalDateTime createdDate;

    @LastModifiedDate // Entity 값이 변경될 때 시간을 자동으로 저장한다.
    private LocalDateTime modifiedDate;
}
