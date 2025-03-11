package com.viktor.oop.bookstore.dto;

import lombok.Data;

@Data
public class BookDto {
    private String title;
    private String author;
    private int yearPublished;
}
