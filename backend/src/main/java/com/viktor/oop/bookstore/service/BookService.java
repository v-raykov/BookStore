package com.viktor.oop.bookstore.service;

import com.viktor.oop.bookstore.model.dto.BookDto;
import com.viktor.oop.bookstore.model.Book;
import com.viktor.oop.bookstore.repository.BookRepository;
import com.viktor.oop.bookstore.repository.DatabaseBookRepository;
import com.viktor.oop.bookstore.repository.InMemoryBookRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final DatabaseBookRepository databaseBookRepository;
    private final InMemoryBookRepository inMemoryBookRepository;
    private final ModelMapper modelMapper;

    private BookRepository bookRepository;

    public List<Book> getBook() {
        return bookRepository.getAllBooks();
    }

    public Book getBookByIsbn(UUID isbn) {
        return bookRepository.getBookByIsbn(isbn);
    }

    public Book addBook(BookDto bookDto) {
        return bookRepository.addBook(modelMapper.map(bookDto, Book.class));
    }

    public List<Book> addBookBulk(List<BookDto> bookDtos) {
        return bookDtos.stream()
                .map(this::addBook)
                .collect(Collectors.toList());
    }

    public Book updateBook(UUID id, BookDto bookDto) {
        var book = getBookByIsbn(id);
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setYearPublished(bookDto.getYearPublished());
        return bookRepository.saveBook(book);
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

    public void switchRepository(boolean useDb) {
        bookRepository = useDb ? databaseBookRepository : inMemoryBookRepository;
    }

    @PostConstruct
    public void init() {
        bookRepository = databaseBookRepository;
    }
}
