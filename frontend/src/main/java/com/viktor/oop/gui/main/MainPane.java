package com.viktor.oop.gui.main;

import com.viktor.oop.gui.all.AllBooksPanel;
import com.viktor.oop.gui.single.BookInfoPanel;

import javax.swing.*;

import java.awt.*;

public class MainPane extends JSplitPane {
    public MainPane() {
        setLayout(new BorderLayout());

        var topPanel = new TopPanel();
        var splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        var allBooksPanel = new AllBooksPanel();
        var bookInfoPanel = new BookInfoPanel();

        allBooksPanel.setSelectListener(bookInfoPanel::displayBookInfo);
        bookInfoPanel.setDeleteListener(allBooksPanel::deleteBook);
        topPanel.setRepoSwitchListener(allBooksPanel::switchRepo);

        splitPane.setLeftComponent(allBooksPanel);
        splitPane.setRightComponent(bookInfoPanel);
        configureSplitPane(splitPane);

        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }

    private void configureSplitPane(JSplitPane splitPane) {
        splitPane.setResizeWeight(2.0 / 3.0);
        splitPane.setEnabled(false);
        splitPane.setBorder(null);
    }
}
