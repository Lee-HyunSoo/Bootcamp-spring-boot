package com.springboot.advanced_jpa.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @MappedSuperclass : 메인 Entity 쪽에 값만 내려주기 위해
 * @EntityListeners : JPA 에서 발생하는 이벤트를 감지
 * (AuditingEntityListener.class) : Auditing 이벤트를 등록
 * 해당 기능을 사용하기 위해서는 Application Class 에 @EnableJpaAuditing 가 등록되어 있어야 한다.
 *
 * 등록 시간, 수정 시간은 거의 반드시 들어가야하는 데이터지만 작성자, 수정자는 필요 없는 경우도 많기 때문에
 * BaseEntity 와 구별해서 구현
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
