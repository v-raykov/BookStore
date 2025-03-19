package com.viktor.oop.gui.web.get.all;

import com.viktor.oop.gui.listener.BookSelectListener;
import com.viktor.oop.model.Book;
import com.viktor.oop.service.BookService;
import com.viktor.oop.service.SearchCriteria;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

public class ScrollPane extends JScrollPane {
    private final BookService bookService;
    private final GridPanel gridPanel;

    public ScrollPane() {
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
        return bookService.getBooksByCriteria(query, searchCriteria);
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

    public void refresh() {
        displayBooksByCriteria("_", SearchCriteria.ALL);
    }
}
