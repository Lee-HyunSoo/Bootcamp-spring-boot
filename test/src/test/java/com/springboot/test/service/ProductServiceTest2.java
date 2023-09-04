package com.springboot.test.service;

import com.springboot.test.data.dto.ProductRequestDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import com.springboot.test.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * 스프링에서 제공하는 테스트 어노테이션을 통해 Mock 객체를 생성하기 위해
 * 큰 차이는 없지만, 스프링을 띄워야 되는 시간이 추가되기 때문에 Mock 객체를 직접 생성하는게 좀 더 빠르다.
 */
@ExtendWith(SpringExtension.class)
@Import({ProductServiceImpl.class})
class ProductServiceTest2 {

    /**
     * 스프링에 Mock 객체를 등록해서 주입
     */
    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

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