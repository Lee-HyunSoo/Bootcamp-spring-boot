package com.springboot.advanced_jpa.domain.entity;

import com.springboot.advanced_jpa.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class ProductTest {

    @Autowired
    ProductRepository productRepository;

    /**
     * auditing Test
     */
    @Test
    void auditing() {
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(100);
        productRepository.save(product);

        Product foundProduct = productRepository.findByName("펜").get(0);
        System.out.println("foundProduct.getCreatedDate() = " + foundProduct.getCreatedDate());
        System.out.println("foundProduct.getLastModifiedDate() = " + foundProduct.getLastModifiedDate());
        System.out.println("foundProduct.getCreateBy() = " + foundProduct.getCreatedBy());
        System.out.println("foundProduct.getLastModifiedBy() = " + foundProduct.getLastModifiedBy());
    }

}