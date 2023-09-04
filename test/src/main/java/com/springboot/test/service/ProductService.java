package com.springboot.test.service;

import com.springboot.test.data.dto.ProductRequestDto;
import com.springboot.test.data.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductRequestDto productRequestDTO);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
