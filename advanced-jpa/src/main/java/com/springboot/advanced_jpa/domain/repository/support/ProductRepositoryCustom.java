package com.springboot.advanced_jpa.domain.repository.support;

import com.springboot.advanced_jpa.domain.entity.Product;

import java.util.List;

/**
 *  Custom 쿼리의 인터페이스
 */
public interface ProductRepositoryCustom {

    List<Product> findByName(String name);
}
