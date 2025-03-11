package com.viktor.oop.gui.main;

import com.viktor.oop.gui.all.AllBooksPanel;
import com.viktor.oop.gui.single.BookInfoPanel;

import javax.swing.*;

public class MainPane extends JSplitPane {
    public MainPane() {
        super(JSplitPane.HORIZONTAL_SPLIT);
        setLeftComponent(new AllBooksPanel());
        setRightComponent(new BookInfoPanel());
        configure();
    }

    private void configure() {
        setResizeWeight(2.0 / 3.0);
        setEnabled(false);
        setBorder(null);
    }
}
