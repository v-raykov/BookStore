package com.viktor.oop.bookstore.repository;

import com.viktor.oop.bookstore.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository implements BookRepository {
    private final Map<UUID, Book> books = new HashMap<>();

    public Book addBook(Book book) {
        books.put(book.getIsbn(), book);
        return book;
    }

    public Book getBookByIsbn(UUID isbn) {
        return books.get(isbn);
    }

    public boolean removeBook(UUID isbn) {
        return books.remove(isbn) != null;
    }

    public List<Book> findBookByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    public List<Book> findBookByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
}
