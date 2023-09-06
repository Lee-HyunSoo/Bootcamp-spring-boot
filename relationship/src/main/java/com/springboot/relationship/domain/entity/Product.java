package com.springboot.relationship.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = { "productDetail", "provider", "category", "producers" }) // ToString 의 제외 대상
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    /**
     * JoinColumn 을 통해 연관관계의 주인을 Product 로 설정
     */
    @OneToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    private List<Producer> producers = new ArrayList<>();

    /**
     * 연관관계 편의 메서드를 통해 해당 메서드에 접근 시 부모 엔티티와 자식 엔티티에 동시에 값을 넣는다.
     */
    // == 연관관계 편의 메서드 ==
    public void changedProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
        productDetail.setProduct(this);
    }

    public void changedProvider(Provider provider) {
        this.provider = provider;
        provider.getProducts().add(this);
    }

    public void changedCategory(Category category) {
        this.category = category;
        category.getProducts().add(this);
    }

    public void changedProducer(Producer producer) {
        producers.add(producer);
    }

}
