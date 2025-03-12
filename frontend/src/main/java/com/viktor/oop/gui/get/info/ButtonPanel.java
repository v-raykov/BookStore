package com.viktor.oop.gui.get.info;

import com.viktor.oop.gui.listener.BookDeleteListener;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ButtonPanel extends JPanel {
    private final JButton button;

    @Setter
    private UUID selectedBookIsbn;

    @Setter
    private BookDeleteListener deleteListener;

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
        button.addActionListener(_ -> {
            if (deleteListener != null) {
                deleteListener.deleteBook(selectedBookIsbn);
            }
        });
        return button;
    }
}
