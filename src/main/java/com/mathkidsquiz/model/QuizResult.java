// src/main/java/com/mathkidsquiz/model/QuizResult.java
package com.mathkidsquiz.model;

import java.io.Serializable;
import java.sql.Timestamp; // For quiz_date

public class QuizResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int userId;
    private int score;
    private int totalQuestions;
    private Timestamp quizDate; // Use java.sql.Timestamp for database TIMESTAMP type

    // Default constructor
    public QuizResult() {
    }

    // Constructor for creating new result (ID and quizDate are often auto-generated/set by DB)
    public QuizResult(int userId, int score, int totalQuestions) {
        this.userId = userId;
        this.score = score;
        this.totalQuestions = totalQuestions;
    }

    // Constructor for retrieving result from database
    public QuizResult(int id, int userId, int score, int totalQuestions, Timestamp quizDate) {
        this.id = id;
        this.userId = userId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.quizDate = quizDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public Timestamp getQuizDate() {
        return quizDate;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public void setQuizDate(Timestamp quizDate) {
        this.quizDate = quizDate;
    }

    @Override
    public String toString() {
        return "QuizResult{" +
               "id=" + id +
               ", userId=" + userId +
               ", score=" + score +
               ", totalQuestions=" + totalQuestions +
               ", quizDate=" + quizDate +
               '}';
    }
}