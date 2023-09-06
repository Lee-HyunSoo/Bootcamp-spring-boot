package com.springboot.relationship.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Column(unique = true)
    private String code;
    private String name;

    /**
     * One 쪽을 연관관계의 주인으로 설정한 경우
     * 리스트를 기준으로 데이터를 조회하기 때문에 데이터가 뻥튀기 되는 효과가 발생한다.
     * 때문에 One 보단 Many 쪽을 연관관계의 주인으로 정하는 것이 좋다.
     */
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "product_id")
//    private List<Product> products = new ArrayList<>();
    
    /**
     * Many 쪽을 연관관계의 주인으로 변경 
     */        
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();
}
