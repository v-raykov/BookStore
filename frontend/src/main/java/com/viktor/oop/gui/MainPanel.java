package com.viktor.oop.gui;

import com.viktor.oop.service.BookService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel extends JPanel {
    private final BookService apiService;

    public MainPanel() {
        apiService = new BookService();
        setLayout(new BorderLayout());
        JButton button = new JButton("Get Books");
        JLabel label = new JLabel("Click the button to fetch data", SwingConstants.CENTER);
        button.addActionListener(e -> {
            try {
                String response = apiService.getBooks().toString();
                label.setText(response);
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }

        });
        add(label, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
    }
}
