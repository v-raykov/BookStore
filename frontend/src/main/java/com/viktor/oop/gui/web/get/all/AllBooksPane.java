package com.viktor.oop.gui.web.get.all;

import com.viktor.oop.gui.listener.ActionListener;
import com.viktor.oop.gui.web.get.info.BookInfoPanel;

import javax.swing.*;

public class AllBooksPane extends JSplitPane {
    private final BooksPanel booksPanel;
    private final BookInfoPanel bookInfoPanel;

    public AllBooksPane() {
        super(JSplitPane.HORIZONTAL_SPLIT);
        booksPanel = new BooksPanel();
        bookInfoPanel = new BookInfoPanel();
        setLeftComponent(booksPanel);
        setRightComponent(bookInfoPanel);
        setListeners();
        configure();
    }

    public void switchRepo(boolean useDb) {
        booksPanel.switchRepo(useDb);
    }

    public void setEditListener(ActionListener editListener) {
        bookInfoPanel.setEditListener(editListener);
    }

    public void refresh() {
        booksPanel.refresh();
        bookInfoPanel.clear();
    }

    private void setListeners() {
        booksPanel.setSelectListener(bookInfoPanel::displayBookInfo);
        bookInfoPanel.setDeleteListener(selectedBookIsbn -> {
            booksPanel.deleteBook(selectedBookIsbn);
            bookInfoPanel.clear();
        });
    }

    private void configure() {
        setResizeWeight(2.0 / 3.0);
        setEnabled(false);
        setBorder(null);
    }
}
