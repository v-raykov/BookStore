package com.viktor.oop.gui.main;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Book Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        add(new MainPanel());
        setVisible(true);
    }
}
