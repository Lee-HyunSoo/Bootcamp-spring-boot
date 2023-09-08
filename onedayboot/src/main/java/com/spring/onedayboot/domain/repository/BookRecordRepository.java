package com.spring.onedayboot.domain.repository;

import com.spring.onedayboot.domain.entity.BookRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRecordRepository extends JpaRepository<BookRecord, Long> {
}
