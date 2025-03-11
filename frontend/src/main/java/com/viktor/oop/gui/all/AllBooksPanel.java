package com.viktor.oop.gui.all;

import com.viktor.oop.model.Book;
import com.viktor.oop.service.BookService;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AllBooksPanel extends JPanel {
    private final BookService bookService;
    public AllBooksPanel() {
        setLayout(new BorderLayout());
        bookService = BookService.getInstance();

        // Search panel at the top
        SearchPanel searchPanel = new SearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        // Main content panel with GridLayout for buttons
        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 10, 10)); // 3x3 grid with spacing

        // Adding buttons to the grid

        try {
            for (Book book : bookService.getBooks()) {
                JButton button = new JButton(book.getTitle());
                gridPanel.add(button);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Adding grid panel to the center
        add(gridPanel, BorderLayout.CENTER);
    }
}
