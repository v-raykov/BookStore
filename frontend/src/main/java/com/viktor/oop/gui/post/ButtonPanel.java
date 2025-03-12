package com.viktor.oop.gui.post;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public abstract class ButtonPanel extends JPanel {
    private JButton button;

    public ButtonPanel() {
        super(new BorderLayout());
        setButton(createButton());
        add(button);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void setFontSize(int size) {
        button.setFont(new Font(button.getFont().getName(), Font.PLAIN, size));
    }

    protected abstract JButton createButton();
}
