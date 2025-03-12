package com.viktor.oop.gui.post;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class CreateSinglePanel extends JPanel {
    private final JButton button;
    private final JPanel formPanel;

    public CreateSinglePanel() {
        setLayout(new BorderLayout());

        formPanel = new BookFormPanel();
        button = createButton();

        add(formPanel, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        addComponentListener(getListener());
    }

    private JPanel createButtonPanel() {
        var buttonPanel = new JPanel(new BorderLayout()); // Assuming ButtonPanel is the same as in CreateBulkPanel
        buttonPanel.add(button);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return buttonPanel;
    }

    private JButton createButton() {
        var button = new JButton("Create");
        button.addActionListener(_ -> {});
        return button;
    }

    private ComponentListener getListener() {
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustFontSize();
            }
        };
    }

    private void adjustFontSize() {
        int width = getWidth();
        int height = getHeight();
        if (width == 0 || height == 0) return;
        int size = Math.min(width, height) / 14;
        button.setFont(new Font(button.getFont().getName(), Font.PLAIN, size));
        for (Component component : formPanel.getComponents()) {
            component.setFont(new Font(component.getFont().getName(), Font.PLAIN, size));
        }
    }
}
