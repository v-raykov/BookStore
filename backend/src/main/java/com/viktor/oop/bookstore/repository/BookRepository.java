package com.viktor.oop.bookstore.repository;

import com.viktor.oop.bookstore.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookRepository {
    Book addBook(Book book);
    boolean removeBook(UUID isbn);
    List<Book> findBookByTitle(String title);
    List<Book> findBookByAuthor(String author);
    List<Book> getAllBooks();
    Book getBookByIsbn(UUID isbn);
}
