package com.viktor.oop.gui.web.post;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public abstract class CreateBookButtonPanel extends JPanel {
    private JButton button;

    public CreateBookButtonPanel() {
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
