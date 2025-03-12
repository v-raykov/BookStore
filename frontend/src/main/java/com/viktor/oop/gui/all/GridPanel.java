package com.viktor.oop.gui.all;

import com.viktor.oop.gui.listener.BookSelectListener;
import com.viktor.oop.model.Book;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Setter
public class GridPanel extends JPanel {
    private BookSelectListener listener;

    public GridPanel() {
        super(new GridLayout(0, 3, 10, 10));
    }

    public void displayBooks(List<Book> books) {
        removeAll();
        addBookButtons(books);
        adjustGridSize(books.size());
        refreshUI();
    }

    private void addBookButtons(List<Book> books) {
        for (Book book : books) {
            var button = new BookButton(book.getTitle());
            button.addActionListener(_ -> notifyListener(book));
            add(button);
        }
    }

    private void notifyListener(Book book) {
        if (listener != null) {
            listener.onBookSelected(book);
        }
    }

    private void adjustGridSize(int bookCount) {
        int rows = (int) Math.ceil(bookCount / 3.0);
        setLayout(new GridLayout(rows, 3, 10, 10));
        setPreferredSize(new Dimension(400, rows * 100));
    }

    private void refreshUI() {
        revalidate();
        repaint();
    }
}
