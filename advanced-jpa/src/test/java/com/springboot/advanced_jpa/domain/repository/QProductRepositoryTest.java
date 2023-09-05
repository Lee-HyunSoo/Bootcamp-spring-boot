package com.springboot.advanced_jpa.domain.repository;

import com.querydsl.core.types.Predicate;
import com.springboot.advanced_jpa.domain.entity.Product;
import com.springboot.advanced_jpa.domain.entity.QProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
@Transactional
class QProductRepositoryTest {

    @Autowired
    QProductRepository qProductRepository;
    
    /**
     * 테스트 시작 전에 호출되어 사용할 데이터를 저장
     */
    @BeforeEach
    void beforeEach() {
        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(100);

        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(5000);
        product2.setStock(300);

        Product product3 = new Product();
        product3.setName("펜");
        product3.setPrice(500);
        product3.setStock(50);

        qProductRepository.save(product1);
        qProductRepository.save(product2);
        qProductRepository.save(product3);
    }

    /**
     * containsIgnoreCase() : 파라미터의 대소문자 구별하지 않고 포함되어 있다면
     * between 을 통해 name 에 '펜' 이 포함되어 있고 price 가 1000 ~ 2500 사이인 데이터 SELECT
     */
    @Test
    void findOne() {
        // given
        Predicate predicate = QProduct.product.name.containsIgnoreCase("펜")
                .and(QProduct.product.price.between(1000, 2500));

        // when
        Optional<Product> foundProduct = qProductRepository.findOne(predicate);

        // then
        if (foundProduct.isPresent()) {
            Product product = foundProduct.get();
            System.out.println("product = " + product);
        }
    }

    /**
     * name 에 '펜' 이 포함되어 있고 price 가 550 ~ 1500 인 데이터들 SELECT
     */
    @Test
    void findAll() {
        // given
        QProduct qProduct = QProduct.product;

        // when
        Iterable<Product> products = qProductRepository.findAll(
                qProduct.name.contains("펜")
                        .and(qProduct.price.between(550, 1500))
        );

        // then
        for (Product product : products) {
            System.out.println("product = " + product);
        }
    }

}