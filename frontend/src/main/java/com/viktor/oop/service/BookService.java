package com.viktor.oop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viktor.oop.model.Book;
import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collections;
import java.util.List;


public class BookService {
    @Getter
    private static final BookService instance = new BookService();
    private static final String BASE_URL = "http://localhost:8080/book";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private BookService() {
        httpClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
    }

    public List<Book> getBooksByCriteria(String query, SearchCriteria criteria) throws IOException, InterruptedException {
        System.out.println(BASE_URL + getEndpoint(query, criteria));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + getEndpoint(query, criteria)))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            if (objectMapper.readTree(response.body()).isArray()) {
                return objectMapper.readValue(response.body(), new TypeReference<>() {});
            } else {
                return Collections.singletonList(objectMapper.readValue(response.body(), new TypeReference<>() {}));
            }
        } else {
            throw new IOException("API Error: " + response.statusCode());
        }
    }

    private String getEndpoint(String query, SearchCriteria criteria) {
        var criteriaEndpoint = criteria.getEndpoint();
        if (criteria != SearchCriteria.ALL) {
            return String.format("/%s%s%s", criteriaEndpoint, criteriaEndpoint.isEmpty() ? "" : "/", query);
        }
        return criteria.getEndpoint();
    }

    public boolean getStatus() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .timeout(Duration.ofSeconds(1)) // Set request timeout
                .GET()
                .build();

        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(1)) // Set connection timeout
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        } catch (Exception e) {
            return false; // Backend is not ready yet
        }
    }
}
