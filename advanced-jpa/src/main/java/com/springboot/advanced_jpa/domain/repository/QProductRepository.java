package com.springboot.advanced_jpa.domain.repository;

import com.springboot.advanced_jpa.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {
}
