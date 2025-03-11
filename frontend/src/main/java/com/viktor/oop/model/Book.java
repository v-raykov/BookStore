package com.viktor.oop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    private UUID isbn;
    private String title;
    private String author;
    private int yearPublished;

    @Override
    public String toString() {
        return String.format("ISBN: %s%n title: %s%n author: %s %n %d year published", isbn, title, author, yearPublished);
    }
}