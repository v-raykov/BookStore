package com.viktor.oop.bookstore.controller;

import com.viktor.oop.bookstore.dto.BookDto;
import com.viktor.oop.bookstore.model.Book;
import com.viktor.oop.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor()
public class BookController {
    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> getBook() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBook());
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
        return ResponseEntity.status(HttpStatus.OK).body(bookService.addBook(bookDto));
    }


}
