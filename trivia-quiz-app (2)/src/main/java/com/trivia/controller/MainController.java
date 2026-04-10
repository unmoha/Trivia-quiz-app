package com.trivia.controller;

import com.trivia.model.QuizSettings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import java.io.IOException;

public class MainController {
    @FXML
    private StackPane rootStack;

    @FXML
    private Button btnPreferences;

    @FXML
    private Button btnStartQuiz;

    private PreferencesController preferencesController;
    private QuizController quizController;

    @FXML
    public void initialize() throws IOException {
        // Load preferences pane
        FXMLLoader prefLoader = new FXMLLoader(getClass().getResource("/fxml/preferences.fxml"));
        Node prefNode = prefLoader.load();
        preferencesController = prefLoader.getController();

        // Load quiz pane
        FXMLLoader quizLoader = new FXMLLoader(getClass().getResource("/fxml/quiz.fxml"));
        Node quizNode = quizLoader.load();
        quizController = quizLoader.getController();

        // Add both panes to stack (preferences on top initially)
        rootStack.getChildren().addAll(quizNode, prefNode);
    }

    @FXML
    private void openPreferences() {
        // bring preferences to front
        rootStack.getChildren().get(1).toFront();
    }

    @FXML
    private void startQuiz() {
        // Get settings from preferences controller
        QuizSettings settings = preferencesController.getSettings();
        if (settings == null) {
            // default settings if none set
            settings = new QuizSettings(10, 0, "any", "multiple");
        }
        quizController.startQuiz(settings);
        // show quiz pane
        rootStack.getChildren().get(0).toFront();
    }
}
