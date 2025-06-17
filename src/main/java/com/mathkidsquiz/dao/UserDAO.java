// src/main/java/com/mathkidsquiz/dao/UserDAO.java
package com.mathkidsquiz.dao;

import com.mathkidsquiz.model.User;
import com.mathkidsquiz.util.DBConnection; // Import DBConnection
import static com.mathkidsquiz.util.DBConnection.getConnection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64; // For encoding byte array to String

public class UserDAO {

    /**
     * Hashes the given plain text password using SHA-256.
     * WARNING: This is INSECURE for real-world password storage without proper salting and iterations.
     * It's used here only to fulfill the "no external downloads" requirement for demonstration.
     * For production, always use robust libraries like BCrypt or Argon2.
     */
    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            // Convert byte array to Base64 String for storage
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not found: " + e.getMessage());
            return null;
        }
    }

    /**
     * Registers a new user by hashing the password and inserting into the database.
     * @param user The User object containing username, plain text password, email, and role.
     * @return true if registration is successful, false otherwise.
     */
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String hashedPassword = hashPassword(user.getPassword());
            if (hashedPassword == null) {
                return false; // Hashing failed
            }

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, hashedPassword); // Store the "hashed" password
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            // This catch block will tell you if, for example, the username is not unique
            System.err.println("Error registering user: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Authenticates a user by checking the username and verifying the hashed password.
     * @param username The username to check.
     * @param plainPassword The plain text password entered by the user.
     * @return The User object if authentication is successful, null otherwise.
     */
    public User loginUser(String username, String plainPassword) {
        String sql = "SELECT id, username, password, email, role FROM users WHERE username = ?";
        User user = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String hashedPasswordFromDB = rs.getString("password");
                String hashedPasswordAttempt = hashPassword(plainPassword);

                // Compare the plain text password's hash with the stored hash
                if (hashedPasswordAttempt != null && hashedPasswordAttempt.equals(hashedPasswordFromDB)) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(hashedPasswordFromDB); // Storing hashed password from DB
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error logging in user: " + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }
    
    /**public User loginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("role") // Ensure this field exists
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/


    /**
     * Retrieves a user by their ID.
     * @param id The ID of the user to retrieve.
     * @return The User object if found, null otherwise.
     */
    public User getUserById(int id) {
        String sql = "SELECT id, username, password, email, role FROM users WHERE id = ?";
        User user = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Retrieves a user by their username.
     * @param username The username of the user to retrieve.
     * @return The User object if found, null otherwise.
     */
    public User getUserByUsername(String username) {
        String sql = "SELECT id, username, password, email, role FROM users WHERE username = ?";
        User user = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user by username: " + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Updates an existing user's information.
     * Note: This method does not hash the password. If updating password, hash it before calling this.
     * @param user The User object with updated information.
     * @return true if update is successful, false otherwise.
     */
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET username = ?, password = ?, email = ?, role = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword()); // Assumes password is already hashed if updated
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());
            pstmt.setInt(5, user.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a user from the database.
     * @param id The ID of the user to delete.
     * @return true if deletion is successful, false otherwise.
     */
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves all users from the database. (Admin function)
     * @return A list of all User objects.
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, username, password, email, role FROM users";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password")); // Storing hashed password
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all users: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    public List<User> selectAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}