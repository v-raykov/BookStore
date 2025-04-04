package com.viktor.oop.gui.main;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final JSplitPane mainPane;

    public MainPanel() {
        mainPane = new MainPane();
        setLayout(new BorderLayout());
        add(mainPane, BorderLayout.CENTER);
    }

    @Override
    public void doLayout() {
        super.doLayout();
        mainPane.setDividerLocation((int) (getWidth() * (2.0 / 3.0)));
    }
}