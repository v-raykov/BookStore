package com.viktor.oop.bookstore.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private String title;
    private String author;
    private int yearPublished;
}
