package com.viktor.oop.gui.web.post;

import com.viktor.oop.model.BookDto;
import com.viktor.oop.service.BookService;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class CreateSinglePanel extends JPanel {
    @Getter
    private final BookFormPanel formPanel;
    private final ButtonPanel buttonPanel;
    private final BookService bookService;

    public CreateSinglePanel() {
        setLayout(new BorderLayout());
        bookService = BookService.getInstance();

        formPanel = new BookFormPanel();
        buttonPanel = getButtonPanel();

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        addComponentListener(getListener());
    }

    protected ButtonPanel getButtonPanel() {
        return new ButtonPanel() {
            @Override
            protected JButton createButton() {
                var button = new JButton("Create");
                button.addActionListener(_ -> createBook());
                return button;
            }
        };
    }

    protected void createBook() {
        bookService.createBook(new BookDto(formPanel.getTitle(), formPanel.getAuthor(), formPanel.getYearPublished()));
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
        buttonPanel.setFontSize(size);
        for (Component component : formPanel.getComponents()) {
            component.setFont(new Font(component.getFont().getName(), Font.PLAIN, size));
        }
    }
}
