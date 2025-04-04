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
        return String.format(
                "Title: %s%nAuthor: %s%nYear Published: %d%n%nISBN:%n%s",
                title, author, yearPublished, isbn);
    }

}