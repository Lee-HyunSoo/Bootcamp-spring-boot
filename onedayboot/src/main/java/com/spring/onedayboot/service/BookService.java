package com.spring.onedayboot.service;

import com.spring.onedayboot.controller.dto.BookRecordDto;
import com.spring.onedayboot.controller.dto.BookRequestDto;
import com.spring.onedayboot.controller.dto.BookResponseDto;
import com.spring.onedayboot.domain.entity.Book;
import com.spring.onedayboot.domain.entity.BookRecord;
import com.spring.onedayboot.domain.repository.BookRecordRepository;
import com.spring.onedayboot.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookRecordRepository bookRecordRepository;

    public List<BookResponseDto> findAll() {
        List<Book> books = bookRepository.findAllByOrderByIdDesc();
        return books
                .stream()
                .map(book -> new BookResponseDto(book.getId(), book.getTitle(), book.getPrice(), book.getBookRecords(), book.getCreateDate()))
                .collect(Collectors.toList());
    }

    public Long insert(BookRequestDto bookRequestDto) {
        Book book = new Book(bookRequestDto.getTitle(), bookRequestDto.getPrice());
        Book savedBook = bookRepository.save(book);
        return savedBook.getId();
    }

    public BookResponseDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setBookResponseDto(book);
        return bookResponseDto;
    }

    public void record(Long id, BookRecordDto bookRecordDto) {
        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        BookRecord bookRecord = new BookRecord(bookRecordDto.getDate(), bookRecordDto.getPage());
        System.out.println("bookRecord = " + bookRecord);
        bookRecord.updatedBook(book);
        bookRecordRepository.save(bookRecord);
    }

    public void update(Long id, BookRequestDto bookRequestDto) {
        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        book.setTitle(bookRequestDto.getTitle());
        book.setPrice(bookRequestDto.getPrice());
    }

    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        bookRepository.delete(book);
    }

}
