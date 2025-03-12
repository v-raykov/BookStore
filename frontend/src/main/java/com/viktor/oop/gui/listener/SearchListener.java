package com.viktor.oop.gui.listener;

import com.viktor.oop.service.SearchCriteria;

public interface SearchListener {
    void onSearch(String query, SearchCriteria criteria);
}
