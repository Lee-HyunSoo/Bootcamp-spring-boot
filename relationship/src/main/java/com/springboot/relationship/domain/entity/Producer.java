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
public class Producer extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "producer_id")
    private Long id;
    private String code;
    private String name;

    @ManyToMany
    private List<Product> products = new ArrayList<>();

    /**
     * 연관관계 편의 메서드를 통해 해당 메서드에 접근 시 부모 엔티티와 자식 엔티티를 동시에 설정한다.
     */
    // == 연관관계 편의 메서드 ==
    public void changedProduct(Product product) {
        products.add(product);
    }

}
