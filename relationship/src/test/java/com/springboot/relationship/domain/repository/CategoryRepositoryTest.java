package com.springboot.relationship.domain.repository;

import com.springboot.relationship.domain.entity.Category;
import com.springboot.relationship.domain.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class CategoryRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Product - Category 단방향 연관관계 (주인쪽에서만 접근해 데이터 조회 가능)
     * 연관관계의 주인 : Product
     *
     * 수업 중 코드. 현재 연관관계의 주인이 Product 이기 때문에 해당 코드는 에러가 난다.
     * 주인 쪽에서 데이터 조회를 해야하는데, Category 쪽에서 조회를 시도했기 때문이다.
     * 양방향 관계로 전환 시 에러가 사라진다.
     */
    @Test
    void oneToManySingle() {
        // given
        Product product = setProduct();
        Category category = setCategory();
        category.getProducts().add(product);

        productRepository.save(product);
        categoryRepository.save(category);

        // when
        Category foundCategory = categoryRepository.findById(category.getId()).orElseThrow(RuntimeException::new);

        // then
        System.out.println("foundCategory = " + foundCategory);
        System.out.println("foundCategory.getProducts() = " + foundCategory.getProducts());
    }

    /**
     * Product - Category 양방향 연관관계
     * 연관관계의 주인 : Product
     *
     * 양방향 연관관계 이기 때문에 데이터 조회는 어느쪽에서든 가능하다.
     */
    @Test
    void manyToOne() {
        // given
        Category category = setCategory();

        Product product = setProduct();
        product.changedCategory(category);

        productRepository.save(product);
        categoryRepository.save(category);

        // when
        Product foundProduct = productRepository.findById(product.getId()).orElseThrow(RuntimeException::new);
        Category foundCategory = categoryRepository.findById(category.getId()).orElseThrow(RuntimeException::new);

        // then
        System.out.println("foundProduct = " + foundProduct);
        System.out.println("foundProduct.getCategory() = " + foundProduct.getCategory());
        System.out.println("foundCategory = " + foundCategory);
        System.out.println("foundCategory.getProducts() = " + foundCategory.getProducts());
    }

    /**
     * 테스트용 Product 및 Category 객체 생성
     */
    private Product setProduct() {
        Product product = new Product();
        product.setName("Spring Boot JPA");
        product.setPrice(5000);
        product.setStock(500);
        return product;
    }

    private Category setCategory() {
        Category category = new Category();
        category.setCode("S1");
        category.setName("도서");
        return category;
    }

}