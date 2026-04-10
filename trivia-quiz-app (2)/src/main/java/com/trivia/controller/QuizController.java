package com.trivia.controller;

import com.trivia.model.ApiResponse;
import com.trivia.model.Question;
import com.trivia.model.QuizSettings;
import com.trivia.service.TriviaAPIClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class QuizController {
    @FXML
    private Label lblQuestion;

    @FXML
    private VBox answersBox;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblScore;

    @FXML
    private Button btnNext;

    private List<Question> questions = new ArrayList<>();
    private int currentIndex = 0;
    private int score = 0;

    private TriviaAPIClient apiClient = new TriviaAPIClient();

    public void startQuiz(QuizSettings settings) {
        lblStatus.setText("Loading questions...");
        lblScore.setText("Score: 0");
        questions.clear();
        currentIndex = 0;
        score = 0;

        CompletableFuture.supplyAsync(() -> {
            try {
                String json = apiClient.fetchQuestions(settings);
                Gson gson = new Gson();
                Type t = new TypeToken<ApiResponse>(){}.getType();
                ApiResponse resp = gson.fromJson(json, ApiResponse.class);
                return resp.getResults();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).thenAccept(qs -> {
            Platform.runLater(() -> {
                if (qs == null || qs.isEmpty()) {
                    lblStatus.setText("Failed to load questions.");
                } else {
                    questions.addAll(qs);
                    lblStatus.setText("");
                    showQuestion(0);
                }
            });
        });
    }

    private void showQuestion(int index) {
        if (index < 0 || index >= questions.size()) return;
        currentIndex = index;
        Question q = questions.get(index);
        lblQuestion.setText((index+1) + ". " + htmlDecode(q.getQuestion()));
        answersBox.getChildren().clear();
        ToggleGroup group = new ToggleGroup();
        List<String> options = new ArrayList<>(q.getIncorrect_answers());
        options.add(q.getCorrect_answer());
        Collections.shuffle(options);
        for (String opt : options) {
            RadioButton rb = new RadioButton(htmlDecode(opt));
            rb.setToggleGroup(group);
            rb.setWrapText(true);
            rb.setOnAction(evt -> {
                boolean correct = opt.equals(q.getCorrect_answer());
                if (correct) {
                    lblStatus.setText("Correct!");
                    score++;
                } else {
                    lblStatus.setText("Incorrect! Correct: " + htmlDecode(q.getCorrect_answer()));
                }
                lblScore.setText("Score: " + score);
                // disable all radios to prevent changes
                answersBox.getChildren().forEach(node -> node.setDisable(true));
            });
            answersBox.getChildren().add(rb);
        }

        btnNext.setOnAction(evt -> {
            int next = currentIndex + 1;
            if (next < questions.size()) {
                showQuestion(next);
            } else {
                lblStatus.setText("Quiz finished. Final score: " + score + " / " + questions.size());
                lblQuestion.setText("Quiz Complete!");
                answersBox.getChildren().clear();
            }
        });
    }

    private String htmlDecode(String s) {
        if (s == null) return "";
        return s.replaceAll("&quot;", '"' + "")
                .replaceAll("&#039;", "'")
                .replaceAll("&amp;", "&")
                .replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">");
    }
}
