package com.viktor.oop.gui.all;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BookButton extends JButton {
    public BookButton(String title) {
        super("<html><center>" + title + "</center></html>");
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        addComponentListener(getComponentResizedListener());
    }

    private ComponentAdapter getComponentResizedListener() {
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeFont();
            }
        };
    }

    private void resizeFont() {
        int width = getWidth();
        int height = getHeight();
        if (width == 0 || height == 0) return;
        setFont(new Font(getFont().getName(), Font.PLAIN, Math.min(width / 10, height / 4)));
    }
}
