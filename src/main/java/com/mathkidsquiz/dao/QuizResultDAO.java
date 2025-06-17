// src/main/java/com/mathkidsquiz/dao/QuizResultDAO.java
package com.mathkidsquiz.dao;

import com.mathkidsquiz.model.QuizResult;
import com.mathkidsquiz.util.DBConnection; // Import DBConnection

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // For getGeneratedKeys
import java.util.ArrayList;
import java.util.List;

public class QuizResultDAO {

    /**
     * Records a new quiz result in the database.
     * @param result The QuizResult object to record.
     * @return true if the result is added successfully, false otherwise.
     */
    public boolean addQuizResult(QuizResult result) {
        String sql = "INSERT INTO quiz_results (user_id, score, total_questions) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, result.getUserId());
            pstmt.setInt(2, result.getScore());
            pstmt.setInt(3, result.getTotalQuestions());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Optionally retrieve the auto-generated ID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    result.setId(rs.getInt(1)); // Set the ID back to the object
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error adding quiz result: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves all quiz results for a specific user.
     * @param userId The ID of the user whose results are to be retrieved.
     * @return A list of QuizResult objects for the user.
     */
    public List<QuizResult> getQuizResultsByUserId(int userId) {
        List<QuizResult> results = new ArrayList<>();
        String sql = "SELECT id, user_id, score, total_questions, quiz_date FROM quiz_results WHERE user_id = ? ORDER BY quiz_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                QuizResult result = new QuizResult();
                result.setId(rs.getInt("id"));
                result.setUserId(rs.getInt("user_id"));
                result.setScore(rs.getInt("score"));
                result.setTotalQuestions(rs.getInt("total_questions"));
                result.setQuizDate(rs.getTimestamp("quiz_date"));
                results.add(result);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving quiz results for user: " + e.getMessage());
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Retrieves all quiz results from the database (e.g., for admin viewing).
     * This method might be extended to include username for better reporting.
     * @return A list of all QuizResult objects.
     */
    public List<QuizResult> getAllQuizResults() {
        List<QuizResult> results = new ArrayList<>();
        // Joining with users table to get username for better display
        String sql = "SELECT qr.id, qr.user_id, u.username, qr.score, qr.total_questions, qr.quiz_date " +
                     "FROM quiz_results qr JOIN users u ON qr.user_id = u.id " +
                     "ORDER BY qr.quiz_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                QuizResult result = new QuizResult();
                result.setId(rs.getInt("id"));
                result.setUserId(rs.getInt("user_id"));
                // You might want to create a new model or extend QuizResult if you need username directly in QuizResult object
                // For now, if you need username on JSP, you'd fetch it via UserDAO or handle it in servlet.
                result.setScore(rs.getInt("score"));
                result.setTotalQuestions(rs.getInt("total_questions"));
                result.setQuizDate(rs.getTimestamp("quiz_date"));
                results.add(result);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all quiz results: " + e.getMessage());
            e.printStackTrace();
        }
        return results;
    }
}