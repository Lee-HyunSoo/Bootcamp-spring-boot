package com.springboot.relationship.domain.repository;

import com.springboot.relationship.domain.entity.Product;
import com.springboot.relationship.domain.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class ProductDetailRepositoryTest {

    @Autowired
    ProductDetailRepository productDetailRepository;
    @Autowired
    ProductRepository productRepository;

    /**
     * Product - ProductDetail 단방향 연관관계 (주인쪽에서만 접근해 데이터 조회 가능)
     * 연관관계의 주인 : Product
     *
     * @OneToOne 관계에서는 어느쪽이던 연관관계의 주인이 될 수 있지만,
     * 이 경우 비교적 접근 가능성이 더 높은 Product 를 연관관계의 주인으로 설정하였다.
     */
    @Test
    void oneToOneSingle() {
        // given
        ProductDetail productDetail = setProductDetail();

        Product product = setProduct();
        product.changedProductDetail(productDetail); // 연관 관계 편의 메서드

        productDetailRepository.save(productDetail);
        productRepository.save(product);

        // when
        Product foundProduct = productRepository.findById(product.getId()).orElseThrow(RuntimeException::new);

        // then
        System.out.println("foundProduct = " + foundProduct);
        System.out.println("foundProduct.getProductDetail() = " + foundProduct.getProductDetail());
    }

    /**
     * Product - ProductDetail 양방향 연관관계
     * 연관관계의 주인 : Product
     *
     * mappedBy 를 통해 ProductDetail 을 통해서도 Product 의 값에 접근 가능하도록 설정하였다.
     */
    @Test
    void oneToOneBoth() {
        // given
        ProductDetail productDetail = setProductDetail();

        Product product = setProduct();
        product.changedProductDetail(productDetail); // 연관 관계 편의 메서드

        productDetailRepository.save(productDetail);
        productRepository.save(product);

        // when
        ProductDetail foundProductDetail = productDetailRepository.findById(productDetail.getId()).orElseThrow(RuntimeException::new);

        // then
        System.out.println("foundProductDetail = " + foundProductDetail);
        System.out.println("foundProductDetail.getProduct() = " + foundProductDetail.getProduct());
    }

    /**
     * 테스트용 Product 및 ProductDetail 생성
     */
    private Product setProduct() {
        Product product = new Product();
        product.setName("Spring Boot JPA");
        product.setPrice(5000);
        product.setStock(500);
        return product;
    }

    private ProductDetail setProductDetail() {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setDescription("Spring Boot JPA Together");
        return productDetail;
    }

}
