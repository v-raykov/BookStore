package com.viktor.oop.bookstore.controller;

import com.viktor.oop.bookstore.model.dto.BookDto;
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
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBook());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Book> getBooks(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookByIsbn(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") UUID id, @RequestBody BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id, bookDto));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String author) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookByAuthor(author));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBookByTitle(@PathVariable String title) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookByTitle(title));
    }

    @PostMapping()
    public ResponseEntity<Book> postBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(bookDto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Book>> postBookBulk(@RequestBody List<BookDto> bookDtos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBookBulk(bookDtos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        return bookService.removeBook(id)
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/db/{useDb}")
    public ResponseEntity<Void> switchRepository(@PathVariable Boolean useDb) {
        bookService.switchRepository(useDb);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/status")
    public ResponseEntity<Void> getStatus() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
