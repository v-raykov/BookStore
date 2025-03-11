package com.viktor.oop;

import com.viktor.oop.gui.main.MainFrame;

import javax.swing.*;

public class FrontendApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}