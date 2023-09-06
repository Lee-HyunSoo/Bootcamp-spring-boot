package com.springboot.relationship.domain.repository;

import com.springboot.relationship.domain.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
