package com.trivia.controller;

import com.trivia.model.QuizSettings;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class PreferencesController {
    @FXML
    private Spinner<Integer> spinnerAmount;

    @FXML
    private ComboBox<String> comboCategory;

    @FXML
    private ComboBox<String> comboDifficulty;

    @FXML
    private ComboBox<String> comboType;

    private QuizSettings settings;

    @FXML
    public void initialize() {
        spinnerAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 10));
        comboCategory.getItems().addAll(
            "Any (0)",
            "General Knowledge (9)",
            "Entertainment: Books (10)",
            "Science & Nature (17)",
            "Computers (18)",
            "Mathematics (19)",
            "Mythology (20)",
            "Sports (21)"
        );
        comboCategory.getSelectionModel().selectFirst();

        comboDifficulty.getItems().addAll("Any", "easy", "medium", "hard");
        comboDifficulty.getSelectionModel().selectFirst();

        comboType.getItems().addAll("Any", "multiple", "boolean");
        comboType.getSelectionModel().selectFirst();
    }

    public QuizSettings getSettings() {
        int amount = spinnerAmount.getValue();
        String categoryItem = comboCategory.getSelectionModel().getSelectedItem();
        int category = 0;
        if (categoryItem != null && categoryItem.contains("(")) {
            String code = categoryItem.substring(categoryItem.lastIndexOf('(')+1, categoryItem.lastIndexOf(')'));
            try {
                category = Integer.parseInt(code);
            } catch (Exception e) {
                category = 0;
            }
        }
        String difficulty = comboDifficulty.getSelectionModel().getSelectedItem();
        if ("Any".equalsIgnoreCase(difficulty)) difficulty = "";
        String type = comboType.getSelectionModel().getSelectedItem();
        if ("Any".equalsIgnoreCase(type)) type = "";
        settings = new QuizSettings(amount, category, difficulty, type);
        return settings;
    }
}

    @FXML
    private void applyAndClose() {
        // currently the main controller handles grabbing settings when Start Quiz is pressed.
        // This method can be used to provide visual feedback or close a dialog.
        // For SPA approach, we simply do nothing here.
    }
