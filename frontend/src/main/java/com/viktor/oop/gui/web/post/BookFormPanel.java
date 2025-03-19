package com.viktor.oop.gui.web.post;

import lombok.Getter;

import javax.swing.*;

public class BookFormPanel extends JPanel {
    private final GroupLayout layout;
    @Getter
    private final JTextField titleField;
    @Getter
    private final JTextField authorField;
    @Getter
    private final JTextField yearField;

    public BookFormPanel() {
        layout = new GroupLayout(this);
        titleField = new JTextField();
        authorField = new JTextField();
        yearField = new JTextField();
        initializePanel();
        initializeLayout();
    }

    private void initializePanel() {
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
    }

    private void initializeLayout() {
        setupLayout(
                new JLabel("Title:"), titleField,
                new JLabel("Author:"), authorField,
                new JLabel("Year Published:"), yearField
        );
    }

    private void setupLayout(JLabel titleLabel, JTextField titleField,
                             JLabel authorLabel, JTextField authorField,
                             JLabel yearLabel, JTextField yearField) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel)
                        .addComponent(titleField)
                        .addComponent(authorLabel)
                        .addComponent(authorField)
                        .addComponent(yearLabel)
                        .addComponent(yearField)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addComponent(titleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(authorLabel)
                        .addComponent(authorField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearLabel)
                        .addComponent(yearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
    }

    public String getTitle() {
        return titleField.getText();
    }

    public String getAuthor() {
        return authorField.getText();
    }

    public int getYearPublished() {
        return Integer.parseInt(yearField.getText());
    }
}

