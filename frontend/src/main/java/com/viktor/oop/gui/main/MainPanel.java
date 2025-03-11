package com.viktor.oop.gui.main;

import com.viktor.oop.service.BookService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel extends JPanel {
    private final BookService bookService;
    private final JSplitPane splitPane;

    public MainPanel() {
        splitPane = new MainPane();
        bookService = BookService.getInstance();

        setLayout(new BorderLayout());

        JButton button = new JButton("Get Books");
        JLabel label = new JLabel("Click the button to fetch data", SwingConstants.CENTER);

        button.addActionListener(e -> {
            try {
                String response = bookService.getBooks().toString();
                label.setText(response);
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }

        });

        add(label, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

        add(splitPane, BorderLayout.CENTER);
    }

    @Override
    public void doLayout() {
        super.doLayout();
        splitPane.setDividerLocation((int) (getWidth() * (2.0 / 3.0)));
    }
}