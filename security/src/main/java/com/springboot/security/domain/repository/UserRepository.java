package com.springboot.security.domain.repository;

import com.springboot.security.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUid(String uid);
}
