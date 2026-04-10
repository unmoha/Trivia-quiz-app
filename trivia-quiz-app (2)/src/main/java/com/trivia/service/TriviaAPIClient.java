package com.trivia.service;

import com.trivia.model.QuizSettings;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class TriviaAPIClient {
    private final HttpClient client = HttpClient.newHttpClient();

    public String fetchQuestions(QuizSettings settings) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        sb.append("https://opentdb.com/api.php?amount=").append(settings.getAmount());
        if (settings.getCategory() > 0) sb.append("&category=").append(settings.getCategory());
        if (settings.getDifficulty() != null && !settings.getDifficulty().isEmpty()) {
            sb.append("&difficulty=").append(URLEncoder.encode(settings.getDifficulty(), StandardCharsets.UTF_8));
        }
        if (settings.getType() != null && !settings.getType().isEmpty()) {
            sb.append("&type=").append(URLEncoder.encode(settings.getType(), StandardCharsets.UTF_8));
        }
        String url = sb.toString();

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() == 200) {
            return resp.body();
        } else {
            throw new IOException("API returned status " + resp.statusCode());
        }
    }
}
