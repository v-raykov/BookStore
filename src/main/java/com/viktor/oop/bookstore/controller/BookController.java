package com.viktor.oop.bookstore.controller;

import com.viktor.oop.bookstore.dto.BookDto;
import com.viktor.oop.bookstore.model.Book;
import com.viktor.oop.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor()
public class BookController {
    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> getBook() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBook());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Book> getBook(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookByIsbn(id));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String author) {
        return ResponseEntity.ok().body(bookService.getBookByAuthor(author));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBookByTitle(@PathVariable String title) {
        return ResponseEntity.ok().body(bookService.getBookByTitle(title));
    }

    @PostMapping()
    public ResponseEntity<Book> postBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok().body(bookService.addBook(bookDto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Book>> postBookBulk(@RequestBody List<BookDto> bookDtos) {
        return ResponseEntity.ok().body(bookService.addBookBulk(bookDtos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        return bookService.removeBook(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



}
