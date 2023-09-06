package com.springboot.relationship.domain.repository;

import com.springboot.relationship.domain.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
