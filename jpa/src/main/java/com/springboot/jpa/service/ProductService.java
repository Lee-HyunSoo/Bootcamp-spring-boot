package com.springboot.jpa.service;

import com.springboot.jpa.data.dto.ProductRequestDto;
import com.springboot.jpa.data.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductRequestDto productRequestDTO);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
