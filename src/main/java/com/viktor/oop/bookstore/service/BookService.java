package com.viktor.oop.bookstore.service;

import com.viktor.oop.bookstore.dto.BookDto;
import com.viktor.oop.bookstore.model.Book;
import com.viktor.oop.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public List<Book> getBook() {
        return bookRepository.getAllBooks();
    }

    public Book addBook(BookDto bookDto) {
        return bookRepository.addBook(modelMapper.map(bookDto, Book.class));
    }

    public List<Book> getBookByAuthor(String author) {
        return bookRepository.findBookByAuthor(author);
    }

    public List<Book> getBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }
}
