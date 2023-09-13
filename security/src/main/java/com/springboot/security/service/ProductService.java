package com.springboot.security.service;

import com.springboot.security.domain.dto.ProductRequestDto;
import com.springboot.security.domain.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductRequestDto productRequestDTO);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;
//    void changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}