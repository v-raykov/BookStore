package com.viktor.oop.gui.web.get.info;

import com.viktor.oop.gui.listener.ActionListener;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

@Setter
public class ActionButton extends JButton {
    private ActionListener listener;
    private UUID selectedBookIsbn;

    public ActionButton(String text) {
        super(text);
        setPreferredSize(new Dimension(0, 60));
        addActionListener(_ -> {
            if (listener != null && selectedBookIsbn != null) {
                listener.performActionOnBook(selectedBookIsbn);
            }
        });
    }
}
