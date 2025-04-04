package com.viktor.oop.gui.web.put;

import com.viktor.oop.gui.listener.RegimeListener;
import com.viktor.oop.gui.main.Regime;
import com.viktor.oop.gui.web.post.BookFormPanel;
import com.viktor.oop.gui.web.post.ButtonPanel;
import com.viktor.oop.gui.web.post.CreateSinglePanel;
import com.viktor.oop.model.Book;
import com.viktor.oop.service.BookService;
import com.viktor.oop.service.SearchCriteria;
import lombok.Setter;

import javax.swing.*;
import java.util.UUID;

public class EditPanel extends CreateSinglePanel {
    private final BookService bookService;
    private final BookFormPanel bookFormPanel;
    private Book book;
    @Setter
    private RegimeListener regimeListener;

    public EditPanel() {
        bookService = BookService.getInstance();
        bookFormPanel = getFormPanel();
    }

    public void setBookById(UUID bookId) {
        book = bookService.getBooksByCriteria(bookId.toString(), SearchCriteria.ISBN).getFirst();
        bookFormPanel.getAuthorField().setText(book.getAuthor());
        bookFormPanel.getTitleField().setText(book.getTitle());
        bookFormPanel.getYearField().setText(String.valueOf(book.getYearPublished()));
        repaint();
    }

    @Override
    protected ButtonPanel getButtonPanel() {
        return new ButtonPanel() {
            @Override
            protected JButton createButton() {
                var button = new JButton("Edit");
                button.addActionListener(_ -> editBook());
                return button;
            }
        };
    }

    private void editBook() {
        book.setAuthor(bookFormPanel.getAuthorField().getText());
        book.setTitle(bookFormPanel.getTitleField().getText());
        book.setYearPublished(Integer.parseInt(bookFormPanel.getYearField().getText()));
        bookService.editBook(book);
        regimeListener.switchRegime(Regime.LIST);
    }
}
