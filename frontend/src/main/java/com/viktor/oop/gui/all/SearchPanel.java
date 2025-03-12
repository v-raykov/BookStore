package com.viktor.oop.gui.all;

import com.viktor.oop.gui.listener.SearchListener;
import com.viktor.oop.service.SearchCriteria;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {
    private final JTextField searchField;
    private final JComboBox<SearchCriteria> criteriaBox;
    private final JButton searchButton;
    @Setter
    private SearchListener listener;

    public SearchPanel() {
        setLayout(new BorderLayout());
        searchField = new JTextField(30);
        criteriaBox = new JComboBox<>(SearchCriteria.values());
        searchButton = new JButton("Search");

        // Search button action
        searchButton.addActionListener(_ -> performSearch());
        add(getSearchBar(), BorderLayout.NORTH);
    }

    private void performSearch() {
        if (listener != null) {
            listener.onSearch(searchField.getText().trim(), (SearchCriteria) criteriaBox.getSelectedItem()); // Notify listener
        }
    }

    private JPanel getSearchBar() {
        var searchBar = new JPanel(new FlowLayout());
        searchBar.add(criteriaBox);
        searchBar.add(searchField);
        searchBar.add(searchButton);
        return searchBar;
    }
}