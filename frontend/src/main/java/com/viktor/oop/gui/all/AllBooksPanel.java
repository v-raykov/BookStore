package com.viktor.oop.gui.all;

import com.viktor.oop.gui.main.BookSelectListener;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Setter
public class AllBooksPanel extends JPanel {
    private final BookGridPanel bookGridPanel;

    public AllBooksPanel() {
        setLayout(new BorderLayout());

        SearchPanel searchPanel = new SearchPanel();
        bookGridPanel = new BookGridPanel();

        add(searchPanel, BorderLayout.NORTH);
        add(bookGridPanel, BorderLayout.CENTER);

        searchPanel.setListener(bookGridPanel::displayBooksByCriteria);
    }

    public void setListener(BookSelectListener listener) {
        bookGridPanel.setListener(listener);
    }
}
