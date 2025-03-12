package com.viktor.oop.gui.all;

import javax.swing.*;
import java.awt.*;

public class AllBooksPanel extends JPanel {
    public AllBooksPanel() {
        setLayout(new BorderLayout());

        var searchPanel = new SearchPanel();
        var bookGridPanel = new BookGridPanel();

        add(searchPanel, BorderLayout.NORTH);
        add(bookGridPanel, BorderLayout.CENTER);

        searchPanel.setListener(bookGridPanel::displayBooksByCriteria);
    }
}
