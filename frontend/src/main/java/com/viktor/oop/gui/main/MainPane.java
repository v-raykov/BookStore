package com.viktor.oop.gui.main;

import com.viktor.oop.gui.all.AllBooksPanel;
import com.viktor.oop.gui.single.BookInfoPanel;

import javax.swing.*;

public class MainPane extends JSplitPane {
    public MainPane() {
        super(JSplitPane.HORIZONTAL_SPLIT);
        var allBooksPanel = new AllBooksPanel();
        var bookInfoPanel = new BookInfoPanel();

        allBooksPanel.setSelectListener(bookInfoPanel::displayBookInfo);
        bookInfoPanel.setDeleteListener(allBooksPanel::deleteBook);

        setLeftComponent(allBooksPanel);
        setRightComponent(bookInfoPanel);
        configure();
    }

    private void configure() {
        setResizeWeight(2.0 / 3.0);
        setEnabled(false);
        setBorder(null);
    }
}
