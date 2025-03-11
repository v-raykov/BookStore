package com.viktor.oop.bookstore.model.dto;

import lombok.Data;

@Data
public class BookDto {
    private String title;
    private String author;
    private int yearPublished;
}
