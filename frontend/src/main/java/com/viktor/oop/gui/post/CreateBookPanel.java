package com.viktor.oop.gui.post;

import javax.swing.*;

public class CreateBookPanel extends JSplitPane {
    public CreateBookPanel() {
        super(JSplitPane.HORIZONTAL_SPLIT);
        setDividerLocation(0.5);
        setResizeWeight(0.5);
        var createSinglePanel = new CreateSinglePanel();
        var createBulkPanel = new CreateBulkPanel();

        setLeftComponent(createSinglePanel);
        setRightComponent(createBulkPanel);
    }
}
