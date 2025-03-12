package com.viktor.oop.gui.main;

import com.viktor.oop.gui.books.AllBooksPanel;
import com.viktor.oop.gui.info.BookInfoPanel;

import javax.swing.*;

import java.awt.*;

public class MainPane extends JSplitPane {
    private final TopPanel topPanel;
    private final AllBooksPanel allBooksPanel;
    private final BookInfoPanel bookInfoPanel;
    private final CreateBookPanel createBookPanel;

    private final JSplitPane splitPane;

    public MainPane() {
        setLayout(new BorderLayout());
        topPanel = new TopPanel();
        allBooksPanel = new AllBooksPanel();
        bookInfoPanel = new BookInfoPanel();
        createBookPanel = new CreateBookPanel();
        splitPane = getSplitPane();
        setListeners();
        addComponents(false);
    }

    private void setListeners() {
        allBooksPanel.setSelectListener(bookInfoPanel::displayBookInfo);
        bookInfoPanel.setDeleteListener(allBooksPanel::deleteBook);
        topPanel.setRepoSwitchListener(allBooksPanel::switchRepo);
        topPanel.setBooksRegimeListener(this::switchRegime);
    }

    private void switchRegime(boolean createMode) {
        removeAll();
        addComponents(createMode);
        revalidate();
        repaint();
    }

    private void addComponents(boolean createMode) {
        add(topPanel, BorderLayout.NORTH);
        add(createMode ? createBookPanel : splitPane, BorderLayout.CENTER);
    }

    private JSplitPane getSplitPane() {
        var splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(allBooksPanel);
        splitPane.setRightComponent(bookInfoPanel);
        configureSplitPane(splitPane);
        return splitPane;
    }

    private void configureSplitPane(JSplitPane splitPane) {
        splitPane.setResizeWeight(2.0 / 3.0);
        splitPane.setEnabled(false);
        splitPane.setBorder(null);
    }
}
