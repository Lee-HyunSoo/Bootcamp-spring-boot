package com.springboot.test.data.repository;

import com.springboot.test.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ProductRepositoryTest2 {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 1. Assertions.assertThat()
     * junit 에서 제공하는 assertEquals 에 비해 테스트 코드 가독성이 좋다.
     * junit 의 assertEquals 는 파라미터의 순서를 헷갈릴 수도 있기 때문이다.
     *
     * 2. findById().orElseThrow(RuntimeException::new);
     * Optional 객체를 반환 받을 시, 해당 객체가 null 일 때 예외처리를 하고 null 이 아니면 .get() 을 하여 내부 객체를 반환한다.
     *
     * 3. Assertions.assertFalse()
     * 인자로 특정 조건 및 boolean 값을 넘기고 return 값이 true 일 때 junit 에러를 발생시킨다.
     */
    @Test
    void basicCRUDTest() {
        // == create ==
        // given
        Product givenProduct = Product.builder()
                .name("노트")
                .price(1000)
                .stock(500)
                .build();
        // when
        Product savedProduct = productRepository.save(givenProduct);

        // then
        assertThat(savedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
        assertThat(savedProduct.getName()).isEqualTo(givenProduct.getName());
        assertThat(savedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
        assertThat(savedProduct.getStock()).isEqualTo(givenProduct.getStock());

        // == read ==
        // when
        Product selectedProduct = productRepository.findById(savedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        // then
        assertThat(selectedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
        assertThat(selectedProduct.getName()).isEqualTo(givenProduct.getName());
        assertThat(selectedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
        assertThat(selectedProduct.getStock()).isEqualTo(givenProduct.getStock());

        // == update ==
        // when
        Product foundProduct = productRepository.findById(selectedProduct.getNumber())
                .orElseThrow(RuntimeException::new);
        foundProduct.setName("장난감");
        Product updatedProduct = productRepository.save(foundProduct);

        // then
        assertEquals(updatedProduct.getName(), "장난감");

        // == delete ==
        // when
        productRepository.delete(updatedProduct);

        // then
        assertFalse(productRepository.findById(selectedProduct.getNumber()).isPresent());
    }

}