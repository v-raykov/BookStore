package com.viktor.oop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viktor.oop.model.Book;
import com.viktor.oop.model.BookDto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class BookService {
    private static BookService instance;

    private static final String BASE_URL = "http://localhost:8080/book";
    private static final String STATUS_URL = BASE_URL + "/status";
    private static final String GET_URL = BASE_URL;
    private static final String ID_URL = GET_URL + "/";
    private static final String POST_URL = BASE_URL;
    private static final String POST_BULK_URL = POST_URL + "/bulk";
    private static final String REPO_URL = BASE_URL + "/db/";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private BookService() {
        httpClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
    }

    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    public boolean getStatus() {
        try {
            requestStatus();
            return true;
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }

    public List<Book> getBooksByCriteria(String query, SearchCriteria criteria) {
        try {
            return requestGet(query, criteria);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBookByIsbn(UUID id) {
        try {
            requestDelete(id.toString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void createBooks(String jsonList) {
        try {
            requestPostBulk(jsonList);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void createBook(BookDto bookDto) {
        try {
            requestPost(bookDto);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchRepo(boolean useDatabase) {
        try {
            requestSwitchRepo(useDatabase);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void requestStatus() throws IOException, InterruptedException {
        validateResponse(httpClient.send(buildRequestStatus(), HttpResponse.BodyHandlers.ofString()));
    }

    private List<Book> requestGet(String query, SearchCriteria criteria) throws IOException, InterruptedException {
        var response = httpClient.send(buildRequestGet(query, criteria), HttpResponse.BodyHandlers.ofString());
        validateResponse(response);
        return mapResponseToBooks(response.body());
    }

    private void requestDelete(String id) throws IOException, InterruptedException {
        validateResponse(httpClient.send(buildRequestDelete(id), HttpResponse.BodyHandlers.ofString()));
    }

    private void requestPostBulk(String jsonList) throws IOException, InterruptedException {
        validateResponse(httpClient.send(buildRequestPostBulk(jsonList), HttpResponse.BodyHandlers.ofString()));
    }

    private void requestPost(BookDto bookDto) throws IOException, InterruptedException {
        validateResponse(httpClient.send(buildRequestPost(bookDto), HttpResponse.BodyHandlers.ofString()));
    }

    private void requestSwitchRepo(boolean useDatabase) throws IOException, InterruptedException {
        httpClient.send(buildRequestSwitchRepo(useDatabase), HttpResponse.BodyHandlers.ofString());
    }

    private void validateResponse(HttpResponse<String> response) throws IOException {
        if (response.statusCode() != 200) {
            throw new IOException("API Error: " + response.statusCode());
        }
    }

    private HttpRequest buildRequestStatus() {
        return HttpRequest.newBuilder()
                .uri(URI.create(STATUS_URL))
                .GET()
                .build();
    }

    private HttpRequest buildRequestGet(String query, SearchCriteria criteria) {
        return HttpRequest.newBuilder()
                .uri(URI.create(GET_URL + getEndpoint(query, criteria)))
                .GET()
                .build();
    }

    private HttpRequest buildRequestDelete(String id) {
        return HttpRequest.newBuilder()
                .uri(URI.create(ID_URL + id))
                .DELETE()
                .build();
    }

    private HttpRequest buildRequestPostBulk(String jsonList) {
        return HttpRequest.newBuilder()
                .uri(URI.create(POST_BULK_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonList))
                .build();
    }

    private HttpRequest buildRequestPost(BookDto bookDto) throws JsonProcessingException {
        return HttpRequest.newBuilder()
                .uri(URI.create(POST_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(bookDto)))
                .build();
    }

    private HttpRequest buildRequestSwitchRepo(boolean useDatabase) {
        return HttpRequest.newBuilder()
                .uri(URI.create(REPO_URL + useDatabase))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();
    }

    private List<Book> mapResponseToBooks(String response) throws IOException {
        if (objectMapper.readTree(response).isArray()) {
            return objectMapper.readValue(response, new TypeReference<>() {
            });
        } else {
            return Collections.singletonList(objectMapper.readValue(response, new TypeReference<>() {
            }));
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
