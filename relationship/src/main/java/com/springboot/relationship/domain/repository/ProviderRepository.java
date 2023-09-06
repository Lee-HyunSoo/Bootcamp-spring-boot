package com.springboot.relationship.domain.repository;

import com.springboot.relationship.domain.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
