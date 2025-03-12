package com.viktor.oop.gui.all;

import com.viktor.oop.model.Book;
import com.viktor.oop.service.BookService;
import com.viktor.oop.service.SearchCriteria;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BookGridPanel extends JPanel {
    private final BookService bookService;

    public BookGridPanel() {
        super(new GridLayout(3, 3, 10, 10));
        bookService = BookService.getInstance();
        displayBooksByCriteria("_", SearchCriteria.ALL);
    }

    public void displayBooksByCriteria(String query, SearchCriteria searchCriteria) {
        removeAll();
        try {
            for (Book book : bookService.getBooksByCriteria(query, searchCriteria)) {
                JButton button = new JButton(book.getTitle());
                add(button);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        revalidate();
        repaint();
    }
}
