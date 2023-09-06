package com.springboot.relationship.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class ProductDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_detail_id")
    private Long id;
    private String description;

    /**
     * 양방향 연관관계 시 주인이 아닌 쪽은 mappedBy를 통해 주인쪽 엔티티를 읽기만 가능
     */
    @OneToOne(mappedBy = "productDetail")
    private Product product;

}
