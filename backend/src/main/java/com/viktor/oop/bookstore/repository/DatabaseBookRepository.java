package com.viktor.oop.bookstore.repository;

import com.viktor.oop.bookstore.model.Book;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Primary
public interface DatabaseBookRepository extends BookRepository, JpaRepository<Book, UUID> {
    @Override
    default Book addBook(Book book) {
        return save(book);
    }

    @Override
    default Book getBookByIsbn(UUID isbn) {
        return findById(isbn).orElse(null);
    }

    @Override
    default boolean removeBook(UUID isbn) {
        if (existsById(isbn)) {
            deleteById(isbn);
            return true;
        }
        return false;
    }

    @Override
    default List<Book> getAllBooks() {
        return findAll();
    }

    @Override
    default List<Book> findBookByTitle(String title) {
        return findAll().stream()
                .filter(book -> book.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    default Book saveBook(Book book) {
        return save(book);
    }
}
