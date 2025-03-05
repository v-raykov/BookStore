package com.viktor.oop.bookstore.service;

import com.viktor.oop.bookstore.dto.BookDto;
import com.viktor.oop.bookstore.model.Book;
import com.viktor.oop.bookstore.repository.DatabaseBookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final DatabaseBookRepository bookRepository;
    private final ModelMapper modelMapper;

    public List<Book> getBook() {
        return bookRepository.getAllBooks();
    }

    public Book getBookByIsbn(UUID isbn) {
        return bookRepository.getBookByIsbn(isbn);
    }

    public Book addBook(BookDto bookDto) {
        return bookRepository.addBook(modelMapper.map(bookDto, Book.class));
    }

    @Transactional
    public List<Book> addBookBulk(List<BookDto> bookDtos) {
        return bookDtos.stream()
                .map(this::addBook)
                .collect(Collectors.toList());
    }

    public List<Book> getBookByAuthor(String author) {
        return bookRepository.findBookByAuthor(author);
    }

    public List<Book> getBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    public boolean removeBook(UUID id) {
        return bookRepository.removeBook(id);
    }

}
