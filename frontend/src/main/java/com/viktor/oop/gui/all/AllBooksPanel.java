package com.viktor.oop.gui.all;

import javax.swing.*;
import java.awt.*;

public class AllBooksPanel extends JPanel {
    public AllBooksPanel() {
        setLayout(new BorderLayout());

        SearchPanel searchPanel = new SearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        JLabel contentLabel = new JLabel("Main Content Area", SwingConstants.CENTER);
        add(contentLabel, BorderLayout.CENTER);
    }
}
