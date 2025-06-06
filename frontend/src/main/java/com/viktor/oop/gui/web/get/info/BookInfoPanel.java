package com.viktor.oop.gui.web.get.info;

import com.viktor.oop.gui.listener.ActionListener;
import com.viktor.oop.model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class BookInfoPanel extends JPanel {
    private final InfoPane infoPane;
    private final ButtonPanel buttonPanel;

    public BookInfoPanel() {
        setLayout(new BorderLayout());
        infoPane = new InfoPane();
        buttonPanel = new ButtonPanel();
        add(infoPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        addComponentListener(getListener());
    }

    public void displayBookInfo(Book book) {
        infoPane.setText(book.toString());
        buttonPanel.setSelectedBookIsbn(book.getIsbn());
    }

    public void setDeleteListener(ActionListener bookDeleteListener) {
        buttonPanel.setDeleteListener(bookDeleteListener);
    }

    public void setEditListener(ActionListener bookEditListener) {
        buttonPanel.setEditListener(bookEditListener);
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
        infoPane.setFontSize(size);
        buttonPanel.setFontSize(size);
    }

    public void clear() {
        infoPane.setText("");
    }
}
