package com.viktor.oop.gui.web.get.info;

import javax.swing.*;
import java.awt.*;

public class InfoPane extends JScrollPane {
    private final JTextArea infoArea;
    public InfoPane() {
        super();
        infoArea = getInfo();
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setViewportView(infoArea);
    }

    public void setText(String text) {
        infoArea.setText(text);
    }

    public void setFontSize(int size) {
        Font newFont = new Font(infoArea.getFont().getName(), Font.PLAIN, size);
        infoArea.setFont(newFont);
    }

    private JTextArea getInfo() {
        var info = new JTextArea();
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setEditable(false);
        return info;
    }
}
