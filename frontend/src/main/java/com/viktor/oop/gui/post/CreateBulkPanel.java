package com.viktor.oop.gui.post;

import com.viktor.oop.gui.get.info.ButtonPanel;
import com.viktor.oop.service.BookService;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class CreateBulkPanel extends JPanel {
    private final JTextArea textArea;
    private final JButton button;
    private final BookService bookService;

    public CreateBulkPanel() {
        setLayout(new BorderLayout());
        bookService = BookService.getInstance();

        textArea = getTextArea();
        button = getButton();

        add(getTextPane(), BorderLayout.CENTER);
        add(getButtonPanel(), BorderLayout.SOUTH);
        addComponentListener(getListener());
    }

    private JPanel getButtonPanel() {
        var buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(button);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return buttonPanel;
    }

    private JButton getButton() {
        var button = new JButton();
        button.setText("Create (via JSON list)");
        button.addActionListener(_ -> createBooks());
        return button;
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
        button.setFont(new Font(button.getFont().getName(), Font.PLAIN, size));
    }
}
