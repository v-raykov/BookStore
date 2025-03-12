package com.viktor.oop.gui.all;

import com.viktor.oop.gui.listener.BookSelectListener;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Setter
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

    public void setListener(BookSelectListener listener) {
        bookPanel.setListener(listener);
    }
}
