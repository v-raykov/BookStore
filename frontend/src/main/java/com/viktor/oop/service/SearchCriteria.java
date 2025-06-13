package com.viktor.oop.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchCriteria {
    ALL(""),
    ISBN("/"),
    TITLE("/title/"),
    AUTHOR("/author/"),;

    private final String endpoint;
}
