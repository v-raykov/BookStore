package com.viktor.oop.gui.all;

import com.viktor.oop.gui.listener.BookSelectListener;
import com.viktor.oop.model.Book;
import com.viktor.oop.service.BookService;
import com.viktor.oop.service.SearchCriteria;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class BookPanel extends JScrollPane {
    private final BookService bookService;
    private final GridPanel gridPanel;

    public BookPanel() {
        gridPanel = new GridPanel();
        bookService = BookService.getInstance();
        configure();
        displayBooksByCriteria("_", SearchCriteria.ALL);
    }

    public void displayBooksByCriteria(String query, SearchCriteria searchCriteria) {
        List<Book> books = fetchBooks(query, searchCriteria);
        gridPanel.displayBooks(books);

    }

    private void configure() {
        setViewportView(gridPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private List<Book> fetchBooks(String query, SearchCriteria searchCriteria) {
        try {
            return bookService.getBooksByCriteria(query, searchCriteria);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSelectListener(BookSelectListener listener) {
        gridPanel.setListener(listener);
    }

    public void deleteBook(UUID isbn) {
        bookService.deleteBookByIsbn(isbn);
        displayBooksByCriteria("_", SearchCriteria.ALL);
    }

    public void switchRepo(boolean useDatabase) {
        bookService.switchRepo(useDatabase);
        displayBooksByCriteria("_", SearchCriteria.ALL);
    }
}
