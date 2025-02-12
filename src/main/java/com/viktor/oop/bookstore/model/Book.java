package com.viktor.oop.bookstore.model;

import com.viktor.oop.bookstore.dto.BookDto;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Book {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private UUID isbn;

    private String title;
    private String author;
    private int yearPublished;
}
