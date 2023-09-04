package com.springboot.test.repository.repository;

import com.springboot.test.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
