package com.viktor.oop.gui.web.get.all;

import com.viktor.oop.gui.listener.BookSelectListener;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class BooksPanel extends JPanel {
    private final ScrollPane scrollPane;

    public BooksPanel() {
        setLayout(new BorderLayout());

        var searchPanel = new SearchPanel();
        scrollPane = new ScrollPane();

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        searchPanel.setListener(scrollPane::displayBooksByCriteria);
    }

    public void setSelectListener(BookSelectListener listener) {
        scrollPane.setSelectListener(listener);
    }

    public void deleteBook(UUID isbn) {
        scrollPane.deleteBook(isbn);
    }

    public void switchRepo(boolean useDatabase) {
        scrollPane.switchRepo(useDatabase);
    }

    public void refresh() {
        scrollPane.refresh();
    }
}
