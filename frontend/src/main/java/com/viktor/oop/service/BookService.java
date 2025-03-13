package com.viktor.oop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viktor.oop.model.Book;
import com.viktor.oop.model.BookDto;
import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    public boolean getStatus() {
        try {
            getBooksByCriteria("_", SearchCriteria.ALL);
            return true;
        } catch (IOException | InterruptedException e) {
            return false;
        }
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

    public void createBooks(String text) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/bulk"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(text))
                .build();
        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void createBook(BookDto bookDto) {
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(bookDto)))
                    .build();
            try {
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (JsonProcessingException e) {
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
        var request = HttpRequest.newBuilder()
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
}
