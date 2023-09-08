package com.spring.onedayboot.controller.dto;

import com.spring.onedayboot.domain.entity.Book;
import com.spring.onedayboot.domain.entity.BookRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {

    private Long id;
    private String title;
    private int price;
    private List<BookRecord> bookRecords = new ArrayList<>();
    private LocalDateTime createDate;

    // == 편의 메서드 ==
    public void setBookResponseDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this. bookRecords = book.getBookRecords();
        this.createDate = book.getCreateDate();
    }
}
