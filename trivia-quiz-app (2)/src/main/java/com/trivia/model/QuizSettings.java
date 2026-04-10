package com.trivia.model;

public class QuizSettings {
    private int amount;
    private int category; // 0 for any
    private String difficulty; // "" for any
    private String type; // "" for any

    public QuizSettings(int amount, int category, String difficulty, String type) {
        this.amount = amount;
        this.category = category;
        this.difficulty = difficulty == null ? "" : difficulty;
        this.type = type == null ? "" : type;
    }

    public int getAmount() { return amount; }
    public int getCategory() { return category; }
    public String getDifficulty() { return difficulty; }
    public String getType() { return type; }
}
