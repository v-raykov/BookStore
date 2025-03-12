package com.viktor.oop.gui.all;

import com.viktor.oop.gui.listener.SearchListener;
import com.viktor.oop.service.SearchCriteria;

import javax.swing.*;
import java.awt.*;
import lombok.Setter;

public class SearchPanel extends JPanel {
    private final JTextField searchField;
    private final JComboBox<SearchCriteria> criteriaBox;
    private final JButton searchButton;

    @Setter
    private SearchListener listener;

    public SearchPanel() {
        setLayout(new BorderLayout());
        searchField = createSearchField();
        criteriaBox = createCriteriaBox();
        searchButton = createSearchButton();
        add(createSearchBar(), BorderLayout.NORTH);
    }

    private JTextField createSearchField() {
        var field = new JTextField(30);
        configureComponentSize(field);
        return field;
    }

    private JComboBox<SearchCriteria> createCriteriaBox() {
        var box = new JComboBox<>(SearchCriteria.values());
        configureComponentSize(box);
        box.addActionListener(_ -> searchField.setText(""));
        return box;
    }

    private JButton createSearchButton() {
        var button = new JButton("Search");
        configureComponentSize(button);
        button.addActionListener(_ -> performSearch());
        return button;
    }

    private void configureComponentSize(JComponent component) {
        int componentHeight = 28;
        var uniformSize = new Dimension(100, componentHeight);
        component.setPreferredSize(uniformSize);
        component.setMinimumSize(uniformSize);
        component.setMaximumSize(new Dimension(Integer.MAX_VALUE, componentHeight));
    }

    private void performSearch() {
        if (listener != null) {
            listener.onSearch(searchField.getText().trim(), (SearchCriteria) criteriaBox.getSelectedItem());
        }
    }

    private JPanel createSearchBar() {
        var searchBar = new JPanel();
        var layout = new GroupLayout(searchBar);
        searchBar.setLayout(layout);
        configureLayout(layout);
        return searchBar;
    }

    private void configureLayout(GroupLayout layout) {
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(criteriaBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchField, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(criteriaBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
    }
}


