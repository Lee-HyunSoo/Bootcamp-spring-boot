package com.springboot.advanced_jpa.domain.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advanced_jpa.domain.entity.Product;
import com.springboot.advanced_jpa.domain.entity.QProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
class ProductRepositoryQueryDSLTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    JPAQueryFactory jpaQueryFactory;

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

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }

    /**
     * JPAQuery 를 사용한 QueryDSL
     * from 절 부터 작성한다.
     */
    @Test
    void jpaQuery() {
        // when
        JPAQuery<Product> query = new JPAQuery<Product>(em);
        QProduct product = QProduct.product;

        List<Product> products = query
                .from(product)
                .where(product.name.eq("펜"))
                .orderBy(product.price.asc())
                .fetch();

        // then
        for (Product p : products) {
            System.out.println("product = " + p);
        }
    }

    /**
     * JPAQueryFactory 를 사용한 QueryDSL
     * 일반적으로 사용하는 방식으로, SELECT 절 부터 작성이 가능하다.
     */
    @Test
    void jpaQueryFactory() {
        // given
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QProduct qProduct = QProduct.product;

        // when
        List<Product> products = queryFactory
                .selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        // then
        for (Product product : products) {
            System.out.println("product = " + product);
        }
    }

    /**
     * 조회 대상이 여러개일 경우 (SELECT 의 대상이 여러개)
     * Tuple 을 사용
     */
    @Test
    void jpaQueryFactoryByTuple() {
        // given
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QProduct qProduct = QProduct.product;

        // when
        List<String> products = queryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        // then
        for (String product : products) {
            System.out.println("product = " + product);
        }

        // when
        List<Tuple> tuples = queryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        // then
        for (Tuple tuple : tuples) {
            System.out.println("tuple = " + tuple);
        }
    }

    /**
     * 매번 JPAQueryFactory 를 선언하기 불편하기 때문에,
     * @Configuration 으로 등록한 JPAQueryFactory 사용
     */
    @Test
    void jpaQueryFactoryByConfiguration() {
        // given
        QProduct qProduct = QProduct.product;

        // when
        List<String> products = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        // then
        for (String product : products) {
            System.out.println("product = " + product);
        }
    }

}