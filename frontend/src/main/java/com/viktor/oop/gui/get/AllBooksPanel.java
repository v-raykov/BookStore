package com.viktor.oop.gui.get;

import com.viktor.oop.gui.listener.BookSelectListener;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class AllBooksPanel extends JPanel {
    private BookPanel bookPanel;

    public AllBooksPanel() {
        setLayout(new BorderLayout());

        var searchPanel = new SearchPanel();
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

    public void refresh() {
        bookPanel.refresh();
    }
}
