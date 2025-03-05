package com.viktor.oop.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    private UUID isbn;

    private String title;
    private String author;
    private int yearPublished;
}
