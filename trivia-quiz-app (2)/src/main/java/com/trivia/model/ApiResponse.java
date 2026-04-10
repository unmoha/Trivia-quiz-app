package com.trivia.model;

import java.util.List;

public class ApiResponse {
    private int response_code;
    private List<Question> results;

    public int getResponse_code() { return response_code; }
    public List<Question> getResults() { return results; }
}
