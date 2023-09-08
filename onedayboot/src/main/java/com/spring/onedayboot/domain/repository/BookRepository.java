package com.spring.onedayboot.domain.repository;

import com.spring.onedayboot.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByOrderByIdDesc();

}
