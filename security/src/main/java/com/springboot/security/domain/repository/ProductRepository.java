package com.springboot.security.domain.repository;

import com.springboot.security.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
