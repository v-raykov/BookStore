package com.viktor.oop.gui.listener;

import com.viktor.oop.service.SearchCriteria;

public interface SearchListener {
    void searchBooks(String query, SearchCriteria criteria);
}
