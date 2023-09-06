package com.springboot.relationship.domain.repository;

import com.springboot.relationship.domain.entity.Producer;
import com.springboot.relationship.domain.entity.Product;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class ProducerRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProducerRepository producerRepository;

    /**
     * Product - Producer 단방향 연관관계 (주인쪽에서만 접근해 데이터 조회 가능)
     * 연관관계의 주인 : @ManyToMany 이기 때문에 주인을 정하지는 않았다.
     * 
     * 현재 상태에서는 연관관계 편의 메서드가 producer 에만 만들어져 있기 때문에
     * ProducerRepository 만 사용
     */
    @Test
    void manyToManySingle() {
        // given
        Product product1 = saveProduct("동글펜", 500, 1000);
        Product product2 = saveProduct("네모 공책", 100, 2000);
        Product product3 = saveProduct("지우개", 152, 1234);

        Producer producer1 = saveProducer("flature");
        Producer producer2 = saveProducer("wikibooks");

        // == 연관관계 편의 메서드 ==
        producer1.changedProduct(product1);
        producer1.changedProduct(product2);
        producer2.changedProduct(product2);
        producer2.changedProduct(product3);

        // when
        List<Producer> producers = producerRepository.saveAll(Lists.newArrayList(producer1, producer2));

        // then
        for (Producer producer : producers) {
            System.out.println("producer.getProducts() = " + producer.getProducts());
        }
    }

    /**
     * Product - Producer 양방향 연관관계
     * 연관관계의 주인 : @ManyToMany 이기 때문에 주인을 정하지는 않았다.
     *
     * 연관관계 편의 메서드를 Product 에도 작성, 어느쪽이던 데이터 조회가 가능하게 하였다.
     */
    @Test
    void manyToManyDouble() {
        // given
        Producer producer1 = saveProducer("flature");
        Producer producer2 = saveProducer("wikibooks");

        Product product1 = saveProduct("동글펜", 500, 1000);
        Product product2 = saveProduct("네모 공책", 100, 2000);
        Product product3 = saveProduct("지우개", 152, 1234);

        // == 연관관계 편의 메서드 ==
        product1.changedProducer(producer1);
        product2.changedProducer(producer1);
        product2.changedProducer(producer2);
        product3.changedProducer(producer2);
        
        producer1.changedProduct(product1);
        producer1.changedProduct(product2);
        producer2.changedProduct(product2);
        producer2.changedProduct(product3);

        // when
        List<Product> products = productRepository.saveAll(Lists.newArrayList(product1, product2, product3));
        List<Producer> producers = producerRepository.saveAll(Lists.newArrayList(producer1, producer2));

        // then
        for (Product product : products) {
            System.out.println("product.getProducers() = " + product.getProducers());
        }

        for (Producer producer : producers) {
            System.out.println("producer.getProducts() = " + producer.getProducts());
        }
    }

    /**
     * 테스트용 Product 및 Producer 생성
     */
    private Product saveProduct(String name, int price, int stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        return productRepository.save(product);
    }

    private Producer saveProducer(String name) {
        Producer producer = new Producer();
        producer.setName(name);
        return producerRepository.save(producer);
    }

}