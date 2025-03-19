package com.viktor.oop.gui.web.get.info;

import com.viktor.oop.gui.listener.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ButtonPanel extends JPanel {
    private final ActionButton editButton;
    private final ActionButton deleteButton;

    public ButtonPanel() {
        super(new GridLayout(2, 1, 0, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        editButton = new ActionButton("Edit");
        deleteButton = new ActionButton("Delete");

        add(editButton);
        add(deleteButton);
    }

    public void setSelectedBookIsbn(UUID selectedBookIsbn) {
        editButton.setSelectedBookIsbn(selectedBookIsbn);
        deleteButton.setSelectedBookIsbn(selectedBookIsbn);
    }

    public void setDeleteListener(ActionListener deleteListener) {
        deleteButton.setListener(deleteListener);
    }

    public void setEditListener(ActionListener editListener) {
        this.editButton.setListener(editListener);
    }


    public void setFontSize(int size) {
        Font newFont = new Font(deleteButton.getFont().getName(), Font.PLAIN, size);
        deleteButton.setFont(newFont);
        editButton.setFont(newFont);
    }
    /*

    private JButton getEditButton() {
        var button = new JButton("Edit");
        button.setPreferredSize(new Dimension(0, 60));
        button.addActionListener(_ -> {
            if (editListener != null) {
                editListener.editBook(selectedBookIsbn);
            }
        });
        return button;
    }

    private JButton getDeleteButton() {
        var button = new JButton("Delete");
        button.setPreferredSize(new Dimension(0, 60));
        button.addActionListener(_ -> {
            if (deleteListener != null) {
                deleteListener.deleteBook(selectedBookIsbn);
            }
        });
        return button;
    }

     */
}
