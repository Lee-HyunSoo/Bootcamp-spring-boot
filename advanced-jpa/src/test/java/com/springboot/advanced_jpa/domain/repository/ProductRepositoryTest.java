package com.springboot.advanced_jpa.domain.repository;

import com.springboot.advanced_jpa.domain.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.data.domain.Sort.*;

@SpringBootTest
@Transactional
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

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
     * List<Product> findByName(String name, Sort sort);
     */
    @Test
    void sortingAndPaging() {
        // given

        // when
        productRepository.findByName("펜", by(Order.asc("price")));
        productRepository.findByName("펜", by(Order.asc("price"), Order.desc("stock")));

        /**
         * Page<Product> findByName(String name, Pageable pageable);
         * of(int page, int size) : 페이지 번호, 페이지 당 데이터 개수
         * of(int page, int size, Sort) : 페이지 번호, 페이지 당 데이터 개수, 정렬
         */
        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0, 2));

        // then
        System.out.println(productRepository.findByName("펜", getSort()));
    }

    /**
     * @Query("select p from Product p where p.name = :name")
     * List<Product> findByName(@Param("name") String name);
     */
    @Test
    void namedQuery() {
        // given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(100);
        productRepository.save(product);

        // when
        List<Product> foundProduct = productRepository.findByName(product.getName());

        // then
        Assertions.assertThat(foundProduct.get(0).getName()).isEqualTo(product.getName());
    }

    /**
     * @Query("select p from Product p where p.name = :name")
     * List<Product> findByName(@Param("name") String name);
     */
    @Test
    void findByName() {
        List<Product> products = productRepository.findByName("펜");
        for (Product product : products) {
            System.out.println("product = " + product);
        }
    }

    /**
     * @Query("select p.name, p.price, p.stock from Product p where p.name = :name")
     * List<Tuple> findByName2(@Param("name") String name);
     */
    @Test
    void findByName2() {
        List<Tuple> products = productRepository.findByName2("펜");
        for (Tuple tuple : products) {
            System.out.println("tuple.name = " + tuple.get(0));
            System.out.println("tuple.price = " + tuple.get(1));
            System.out.println("tuple.stock = " + tuple.get(2));
        }
    }

    /**
     * 정렬 조건으로 줄 Sort 객체를 return
     */
    private Sort getSort() {
        return Sort.by(
                Order.asc("price"),
                Order.desc("stock")
        );
    }

}