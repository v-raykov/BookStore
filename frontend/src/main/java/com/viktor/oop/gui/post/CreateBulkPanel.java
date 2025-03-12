package com.viktor.oop.gui.post;

import com.viktor.oop.service.BookService;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class CreateBulkPanel extends JPanel {
    private final JTextArea textArea;
    private final ButtonPanel buttonPanel;
    private final BookService bookService;

    public CreateBulkPanel() {
        setLayout(new BorderLayout());
        bookService = BookService.getInstance();
        buttonPanel = getButtonPanel();
        textArea = getTextArea();
        add(getTextPane(), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        addComponentListener(getListener());
    }

    private ButtonPanel getButtonPanel() {
        return new ButtonPanel() {
            @Override
            protected JButton createButton() {
                var button = new JButton("Create (via JSON list)");
                button.addActionListener(_ -> createBooks());
                return button;
            }
        };
    }

    private void createBooks() {
        bookService.createBooks(textArea.getText());
    }

    private JScrollPane getTextPane() {
        var scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return scrollPane;
    }

    private JTextArea getTextArea() {
        var textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    private ComponentListener getListener() {
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustFontSize();
            }
        };
    }

    private void adjustFontSize() {
        int width = getWidth();
        int height = getHeight();
        if (width == 0 || height == 0) return;
        int size = Math.min(width, height) / 14;
        textArea.setFont(new Font(textArea.getFont().getName(), Font.PLAIN, (size / 3) * 2));
        buttonPanel.setFontSize(size);
    }
}
