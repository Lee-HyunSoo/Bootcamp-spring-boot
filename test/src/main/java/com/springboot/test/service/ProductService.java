package com.springboot.test.service;

import com.springboot.test.repository.dto.ProductRequestDto;
import com.springboot.test.repository.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductRequestDto productRequestDTO);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
