package com.springboot.test.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "product")
public class Product {

    /**
     * @Id 를 통해 Primary Key 로 설정
     * @GeneratedValue 를 통해 기본 키 생성 전략을 IDENTITY 로 설정
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    /**
     * @Column(nullable = false) 를 통해 DB 내 각 컬럼에 NOT NULL 설정 부여
     */
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;


}
