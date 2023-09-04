package com.springboot.test.service.impl;

import com.springboot.test.data.dto.ProductRequestDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ProductServiceTest {

    /**
     * mock() 을 통해 Mock 객체로 ProductRepository 주입
     */
    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductServiceImpl productService;

    /**
     * 테스트 시작 전 ProductService 초기화
     * 현재 테스트 환경은 @SpringBootTest 의 관리하에 있지 않기 때문에 @Autowired 대신 테스트 시작 전 주입을 해줘야한다.
     */
    @BeforeEach
    public void setUpTest() {
        productService = new ProductServiceImpl(productRepository);
    }

    /**
     * 1. Mockito.when().thenReturn()
     * when 메서드 내부에 있는 동작을 하면, thenReturn() 을 통해 값을 return
     *
     * 2. assertEquals
     * when 절 에서 설정한 productResponseDto 와, given 절에서 설정한 givenProduct 의 값을 비교
     *
     * 3. any()
     * Mockito 의 ArgumentMatchers 에서 제공하는 메서드
     * Mock 객체의 동작을 정의하거나 검증하는 단계에서 조건으로 특정 매개변수의 전달을 설정하지 않고,
     * 메서드의 실행만을 확인하거나 클래스 객체 (현재 상황은 Entity Class) 를 매개변수로 전달받는 상황에 사용
     */
    @Test
    void getProductTest() {
        // given
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("펜");
        givenProduct.setPrice(1000);
        givenProduct.setStock(1234);

        // when
        Mockito.when(productRepository.findById(123L))
                .thenReturn(Optional.of(givenProduct));
        ProductResponseDto productResponseDto = productService.getProduct(123L);

        // then
        assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        assertEquals(productResponseDto.getName(), givenProduct.getName());
        assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        verify(productRepository).findById(123L);
    }

    @Test
    void saveProductTest() {
        // when
        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        ProductResponseDto productResponseDto = productService
                .saveProduct(new ProductRequestDto("펜", 1000, 1234));

        // then
        assertEquals(productResponseDto.getName(), "펜");
        assertEquals(productResponseDto.getPrice(), 1000);
        assertEquals(productResponseDto.getStock(), 1234);

        verify(productRepository).save(any());
    }

}