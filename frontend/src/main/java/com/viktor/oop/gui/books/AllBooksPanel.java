package com.viktor.oop.gui.books;

import com.viktor.oop.gui.listener.BookSelectListener;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class AllBooksPanel extends JPanel {
    private final BookPanel bookPanel;

    public AllBooksPanel() {
        setLayout(new BorderLayout());

        SearchPanel searchPanel = new SearchPanel();
        bookPanel = new BookPanel();

        add(searchPanel, BorderLayout.NORTH);
        add(bookPanel, BorderLayout.CENTER);

        searchPanel.setListener(bookPanel::displayBooksByCriteria);
    }

    public void setSelectListener(BookSelectListener listener) {
        bookPanel.setSelectListener(listener);
    }

    public void deleteBook(UUID isbn) {
        bookPanel.deleteBook(isbn);
    }

    public void switchRepo(boolean useDatabase) {
        bookPanel.switchRepo(useDatabase);
    }
}
