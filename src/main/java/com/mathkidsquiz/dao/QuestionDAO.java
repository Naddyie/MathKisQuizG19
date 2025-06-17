// src/main/java/com/mathkidsquiz/dao/QuestionDAO.java
package com.mathkidsquiz.dao;

import com.mathkidsquiz.model.Question;
import com.mathkidsquiz.util.DBConnection; // Import DBConnection

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    /**
     * Adds a new question to the database.
     * @param question The Question object to add.
     * @return true if the question is added successfully, false otherwise.
     */
    public boolean addQuestion(Question question) {
        // Corrected column name: correct_answer
        String sql = "INSERT INTO questions (question_text, option_a, option_b, option_c, option_d, correct_answer, difficulty) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, question.getQuestionText());
            pstmt.setString(2, question.getOptionA());
            pstmt.setString(3, question.getOptionB());
            pstmt.setString(4, question.getOptionC());
            pstmt.setString(5, question.getOptionD());
            pstmt.setString(6, question.getCorrectOption());
            pstmt.setString(7, question.getDifficulty());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error adding question: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves a question by its ID.
     * @param id The ID of the question to retrieve.
     * @return The Question object if found, null otherwise.
     */
    public Question getQuestionById(int id) {
        // Corrected column name: correct_answer
        String sql = "SELECT id, question_text, option_a, option_b, option_c, option_d, correct_answer, difficulty FROM questions WHERE id = ?";
        Question question = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestionText(rs.getString("question_text"));
                question.setOptionA(rs.getString("option_a"));
                question.setOptionB(rs.getString("option_b"));
                question.setOptionC(rs.getString("option_c"));
                question.setOptionD(rs.getString("option_d"));
                // Corrected column name: correct_answer
                question.setCorrectOption(rs.getString("correct_answer"));
                question.setDifficulty(rs.getString("difficulty"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving question by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return question;
    }

    /**
     * Retrieves all questions from the database.
     * @return A list of all Question objects.
     */
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        // Corrected column name: correct_answer
        String sql = "SELECT id, question_text, option_a, option_b, option_c, option_d, correct_answer, difficulty FROM questions";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestionText(rs.getString("question_text"));
                question.setOptionA(rs.getString("option_a"));
                question.setOptionB(rs.getString("option_b"));
                question.setOptionC(rs.getString("option_c"));
                question.setOptionD(rs.getString("option_d"));
                // Corrected column name: correct_answer
                question.setCorrectOption(rs.getString("correct_answer"));
                question.setDifficulty(rs.getString("difficulty"));
                questions.add(question);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all questions: " + e.getMessage());
            e.printStackTrace();
        }
        return questions;
    }

    /**
     * Retrieves a specified number of random questions.
     * @param limit The number of random questions to retrieve.
     * @return A list of random Question objects.
     */
    public List<Question> getRandomQuestions(int limit) {
        List<Question> questions = new ArrayList<>();
        // Note: ORDER BY RAND() can be slow on very large tables. For small quizzes, it's fine.
        // Corrected column name: correct_answer
        String sql = "SELECT id, question_text, option_a, option_b, option_c, option_d, correct_answer, difficulty FROM questions ORDER BY RAND() LIMIT ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestionText(rs.getString("question_text"));
                question.setOptionA(rs.getString("option_a"));
                question.setOptionB(rs.getString("option_b"));
                question.setOptionC(rs.getString("option_c"));
                question.setOptionD(rs.getString("option_d"));
                // Corrected column name: correct_answer
                question.setCorrectOption(rs.getString("correct_answer"));
                question.setDifficulty(rs.getString("difficulty"));
                questions.add(question);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving random questions: " + e.getMessage());
            e.printStackTrace();
        }
        return questions;
    }

    /**
     * Updates an existing question in the database.
     * @param question The Question object with updated information.
     * @return true if the update is successful, false otherwise.
     */
    public boolean updateQuestion(Question question) {
        // Corrected column name: correct_answer
        String sql = "UPDATE questions SET question_text = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, correct_answer = ?, difficulty = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, question.getQuestionText());
            pstmt.setString(2, question.getOptionA());
            pstmt.setString(3, question.getOptionB());
            pstmt.setString(4, question.getOptionC());
            pstmt.setString(5, question.getOptionD());
            pstmt.setString(6, question.getCorrectOption());
            pstmt.setString(7, question.getDifficulty());
            pstmt.setInt(8, question.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating question: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a question from the database.
     * @param id The ID of the question to delete.
     * @return true if the deletion is successful, false otherwise.
     */
    public boolean deleteQuestion(int id) {
        String sql = "DELETE FROM questions WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting question: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}