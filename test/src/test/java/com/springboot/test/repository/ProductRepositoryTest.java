package com.springboot.test.repository;

import com.springboot.test.repository.entity.Product;
import com.springboot.test.repository.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 해당 어노테이션을 통해 DB 변경 가능
 * 기본 값은 Replace.ANY 이고, 이 경우 임베디드 메모리 DB (H2 DB) 를 사용한다.
 * Replace.NONE 을 사용할 시, 어플리케이션에서 실제로 사용하는 DB 로 테스트를 한다.
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ProductRepositoryTest {

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