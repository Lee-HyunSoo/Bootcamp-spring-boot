package com.springboot.security.service.impl;

import com.springboot.security.domain.dto.ProductRequestDto;
import com.springboot.security.domain.dto.ProductResponseDto;
import com.springboot.security.domain.entity.Product;
import com.springboot.security.domain.repository.ProductRepository;
import com.springboot.security.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    @Override
    public ProductResponseDto getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(RuntimeException::new);
        return setProductResponseDto(product);
    }
    @Override
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
        Product product = setProduct(productRequestDto);
        Product savedProduct = productRepository.save(product);
        return setProductResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto changeProductName(Long productId, String name) {
        Product changedProduct = productRepository.findById(productId).orElseThrow(RuntimeException::new);
        changedProduct.setName(name);
        return setProductResponseDto(changedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    private ProductResponseDto setProductResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());
        return productResponseDto;
    }

    private Product setProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setStock(productRequestDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());
        return product;
    }
}