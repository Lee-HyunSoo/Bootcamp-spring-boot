package com.springboot.test.repository;

import com.springboot.test.repository.entity.Product;
import com.springboot.test.repository.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JPA 와 관련 된 설정만 로드
 * @Transactional 을 포함하고 있기 때문에 테스트 종료 시 자동 Rollback
 * 기본 값으로 임베디드 DB 사용 (H2 DB), 다른 DB 를 사용하려면 추가 설정 필요
 * 해당 어노테이션을 통해 JPA Repository 를 주입
 */
@DataJpaTest
class ProductRepositoryTestByH2 {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTest() {
        // given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);
        Product savedProduct = productRepository.save(product);

        // when

        // then
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getStock(), savedProduct.getStock());
    }

    @Test
    void selectTest() {
        // given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);
        /**
         * 트랜잭션 종료 전에 먼저 DB 에 flush 를 날리기 위해 saveAndFlush() 사용
         */
        Product savedProduct = productRepository.saveAndFlush(product);

        // when
        /**
         * saveAndFlush() 를 통해 먼저 flush 를 날려 저장했기 때문에 조회가 가능하다.
         */
        Product foundProduct = productRepository.findById(savedProduct.getNumber()).get();

        // then
        assertEquals(savedProduct.getName(), foundProduct.getName());
        assertEquals(savedProduct.getPrice(), foundProduct.getPrice());
        assertEquals(savedProduct.getStock(), foundProduct.getStock());
    }

}