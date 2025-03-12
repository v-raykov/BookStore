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
import java.util.UUID;


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
        var response = getResponse(query, criteria);
        if (response.statusCode() == 200) {
            return getBooks(response.body());
        } else {
            throw new IOException("API Error: " + response.statusCode());
        }
    }

    public void deleteBookByIsbn(UUID id) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id.toString()))
                .DELETE()
                .build();
        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void switchRepo(boolean useDatabase) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/db/" + useDatabase))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse<String> getResponse(String query, SearchCriteria criteria) throws IOException, InterruptedException {
        System.out.println(BASE_URL + getEndpoint(query, criteria));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + getEndpoint(query, criteria)))
                .GET()
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private List<Book> getBooks(String response) throws IOException {
        if (objectMapper.readTree(response).isArray()) {
            return objectMapper.readValue(response, new TypeReference<>() {});
        } else {
            return Collections.singletonList(objectMapper.readValue(response, new TypeReference<>() {}));
        }
    }

    private String getEndpoint(String query, SearchCriteria criteria) {
        var criteriaEndpoint = criteria.getEndpoint();
        if (criteria != SearchCriteria.ALL) {
            return criteriaEndpoint + query.replace(" ", "%20");
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
