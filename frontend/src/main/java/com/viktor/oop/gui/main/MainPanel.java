package com.viktor.oop.gui.main;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final JSplitPane splitPane;

    public MainPanel() {
        splitPane = new MainPane();
        setLayout(new BorderLayout());
        add(splitPane, BorderLayout.CENTER);
    }

    @Override
    public void doLayout() {
        super.doLayout();
        splitPane.setDividerLocation((int) (getWidth() * (2.0 / 3.0)));
    }
}