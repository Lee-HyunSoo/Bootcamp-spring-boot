package com.springboot.relationship.domain.repository;

import com.springboot.relationship.domain.entity.Product;
import com.springboot.relationship.domain.entity.Provider;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class ProviderRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProviderRepository providerRepository;

    /**
     * Product - Provider 단방향 연관관계 (주인쪽에서만 접근해 데이터 조회 가능)
     * 연관관계의 주인 : Product
     */
    @Test
    void manyToOneSingle() {
        // given
        Provider provider = setProvider("TEST 물산");

        Product product = setProduct("가위", 5000, 500);
        product.changedProvider(provider);

        providerRepository.save(provider);
        productRepository.save(product);

        // when
        Product foundProduct = productRepository.findById(product.getId()).orElseThrow(RuntimeException::new);

        // then
        System.out.println("foundProduct = " + foundProduct);
        System.out.println("foundProduct.getProvider() = " + foundProduct.getProvider());
    }

    /**
     * Product - Provider 양방향 연관관계
     * 연관관계의 주인 : Product
     *
     * mappedBy 를 통해 Provider 을 통해서도 Product 의 값에 접근 가능하도록 설정
     */
    @Test
    void manyToOneBoth() {
        // given
        Provider provider = setProvider("TEST 물산");

        Product product = setProduct("가위", 5000, 500);
        product.changedProvider(provider);

        providerRepository.save(provider);
        productRepository.save(product);

        // when
        Provider fountProvider = providerRepository.findById(provider.getId()).orElseThrow(RuntimeException::new);

        // then
        System.out.println("fountProvider = " + fountProvider);
        System.out.println("fountProvider.getProducts() = " + fountProvider.getProducts());
    }

    /**
     * cascade = CascadeType.PERSIST
     * 부모 엔티티 쪽에 해당 옵션이 걸려 있다면 부모 엔티티에 변화가 있을 때 자식도 함께 변한다.
     * 자식 엔티티 쪽에 해당 옵션이 걸려 있다면 자식 엔티티에 변화가 있을 때 부모도 함께 변한다.
     */
    @Test
    void cascade() {
        // given
        Provider provider = setProvider("새로운 공급업체");
        Product product1 = setProduct("상품1", 1000, 1000);
        Product product2 = setProduct("상품2", 500, 1500);
        Product product3 = setProduct("상품3", 750, 500);

        // == 연관관계 편의 메서드 ==
        product1.changedProvider(provider);
        product2.changedProvider(provider);
        product3.changedProvider(provider);

        // when
        provider.getProducts().addAll(Lists.newArrayList(product1, product2, product3));
        providerRepository.save(provider);

        // then
    }

    /**
     * orphanRemoval = true
     * 부모 엔티티가 DB 에서 삭제된다면, 자식 엔티티도 함께 삭제된다.
     */
    @Test
    void orphanRemoval() {
        // given
        Provider provider = setProvider("새로운 공급업체");
        Product product1 = setProduct("상품1", 1000, 1000);
        Product product2 = setProduct("상품2", 500, 1500);
        Product product3 = setProduct("상품3", 750, 500);

        // == 연관관계 편의 메서드 ==
        product1.changedProvider(provider);
        product2.changedProvider(provider);
        product3.changedProvider(provider);

        // when
        provider.getProducts().addAll(Lists.newArrayList(product1, product2, product3));
        providerRepository.saveAndFlush(provider);

        // then
        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);

        Provider foundProvider = providerRepository.findById(provider.getId()).orElseThrow(RuntimeException::new);
        foundProvider.getProducts().remove(0);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);
    }

    /**
     * 테스트용 Product 및 Provider 생성
     */
    private Product setProduct(String name, int price, int stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        return product;
    }

    private Provider setProvider(String name) {
        Provider provider = new Provider();
        provider.setName(name);
        return provider;
    }

}