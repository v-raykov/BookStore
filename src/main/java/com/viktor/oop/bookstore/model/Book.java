package com.viktor.oop.bookstore.model;

import com.viktor.oop.bookstore.dto.BookDto;
import lombok.*;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Book {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private UUID isbn;

    private String title;
    private String author;
    private int yearPublished;

    public Book(UUID isbn, String title, String author, int yearPublished) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public Book(UUID isbn, BookDto bookDto) {
        this(UUID.randomUUID(), bookDto.getTitle(), bookDto.getAuthor(), bookDto.getYearPublished());
    }
}
