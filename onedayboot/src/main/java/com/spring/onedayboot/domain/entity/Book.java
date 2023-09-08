package com.spring.onedayboot.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;
    private String title;
    private int price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookRecord> bookRecords = new ArrayList<>();

    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }
}
