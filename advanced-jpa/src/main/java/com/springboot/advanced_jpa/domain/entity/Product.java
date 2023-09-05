package com.springboot.advanced_jpa.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

}
