package com.viktor.oop.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchCriteria {
    ALL(""),
    ID("/"),
    TITLE("/title/"),
    AUTHOR("/author/"),;

    private final String endpoint;
}
