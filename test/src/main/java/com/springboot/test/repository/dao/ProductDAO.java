package com.springboot.test.repository.dao;

import com.springboot.test.repository.entity.Product;

public interface ProductDAO {

    Product insertProduct(Product product);

    Product selectProduct(Long number);

    Product updateProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
