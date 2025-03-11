package com.viktor.oop.gui.all;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {
    private final JTextField searchField;
    private final JComboBox<String> criteriaBox;
    private final JButton searchButton;

    public SearchPanel() {
        setLayout(new BorderLayout());
        searchField = new JTextField(30);
        criteriaBox = new JComboBox<>(new String[]{"All", "Title", "Author", "Category"});
        searchButton = new JButton("Search");

        // Search button action
        searchButton.addActionListener(_ -> {
            String query = searchField.getText().trim();
            String criteria = (String) criteriaBox.getSelectedItem();
            performSearch(query, criteria);
        });
        add(getSearchPanel(), BorderLayout.NORTH);
    }

    // Dummy search function
    private void performSearch(String query, String criteria) {
        System.out.println("Searching for: " + query + " in " + criteria);
        // logic to filter data from a list or database.
    }

    private JPanel getSearchPanel() {
        JPanel searchBarPanel = new JPanel(new FlowLayout());
        searchBarPanel.add(criteriaBox);
        searchBarPanel.add(searchField);
        searchBarPanel.add(searchButton);
        return searchBarPanel;
    }
}