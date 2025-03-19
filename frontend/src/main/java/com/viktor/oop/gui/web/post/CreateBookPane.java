package com.viktor.oop.gui.web.post;

import com.viktor.oop.gui.listener.RegimeListener;
import com.viktor.oop.gui.main.Regime;

import javax.swing.*;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class CreateBookPane extends JSplitPane {
    private final CreateBulkPanel createBulkPanel;
    private final CreateSinglePanel createSinglePanel;
    public CreateBookPane() {
        super(JSplitPane.HORIZONTAL_SPLIT);
        setResizeWeight(0.5);
        createSinglePanel = new CreateSinglePanel();
        createBulkPanel = new CreateBulkPanel();
        configure();
        addComponentListener(getResizeListener());
    }

    private void configure() {
        setLeftComponent(createSinglePanel);
        setRightComponent(createBulkPanel);
        setEnabled(false);
        setDividerLocation(0);
    }

    private ComponentListener getResizeListener() {
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setDividerLocation(getWidth() / 2);
            }
        };
    }
}

