package com.spring.onedayboot.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = "book")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookRecord {

    @Id
    @GeneratedValue
    @Column(name = "book_record_id")
    private Long id;
    private String date;
    private String page;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public BookRecord(String date, String page) {
        this.date = date;
        this.page = page;
    }

    // == 연관관계 편의 메서드 ==
    public void updatedBook(Book book) {
        this.book = book;
        book.getBookRecords().add(this);
    }
}
