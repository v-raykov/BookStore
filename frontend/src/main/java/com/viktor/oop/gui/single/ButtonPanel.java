package com.viktor.oop.gui.single;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private final JButton button;

    public ButtonPanel() {
        super(new BorderLayout());
        button = getButton();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(button, BorderLayout.CENTER);
    }

    public void setFontSize(int size) {
        Font newFont = new Font(button.getFont().getName(), Font.PLAIN, size);
        button.setFont(newFont);
    }

    private JButton getButton() {
        var button = new JButton("Delete");
        button.setPreferredSize(new Dimension(0, 60));
        return button;
    }
}
