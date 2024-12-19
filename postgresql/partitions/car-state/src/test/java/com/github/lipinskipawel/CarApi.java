package com.github.lipinskipawel;

import com.github.lipinskipawel.db.Car;
import com.github.lipinskipawel.json.Parser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.newHttpClient;
import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static java.net.http.HttpRequest.newBuilder;

public final class CarApi {
    private final Parser parser;
    private final HttpClient httpClient;
    private final String baseUrl;

    public CarApi(int port, Parser parser) {
        this.parser = parser;
        this.httpClient = newHttpClient();
        this.baseUrl = "http://localhost:" + port;
    }

    public HttpResponse<String> get(String url) throws IOException, InterruptedException {
        final var request = newBuilder()
            .uri(URI.create(baseUrl + url))
            .GET()
            .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> post(String url, Car body) throws IOException, InterruptedException {
        final var request = newBuilder()
            .uri(URI.create(baseUrl + url))
            .POST(ofString(parser.parse(body)))
            .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
