<%-- 
    Document   : register
    Created on : Jun 11, 2025, 1:20:46 AM
    Author     : Nadiah Binti Abdul Latif
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Register - MathKids Quiz</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="js/validation.js"></script>
    </head>
    <body>
        <div class="container">
            <h2>Register for MathKids Quiz</h2>
            <form action="<%= request.getContextPath() %>/register" method="post" onsubmit="return validateRegistrationForm()">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="confirm_password">Confirm Password:</label>
                    <input type="password" id="confirm_password" name="confirm_password" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label class="radio-inline" for="role">Role:</label>
                        <input type="radio" name="role" value="student" required> Student
                        <input type="radio" name="role" value="admin"> Admin
                    </label>
                </div>

                <% if (request.getAttribute("errorMessage") != null) { %>
                    <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
                <% } %>
                <button type="submit" class="button button-primary">Register</button>
            </form>
            <p>Already have an account? <a href="<%= request.getContextPath() %>/login">Login here</a></p>
        </div>
    </body>
</html>
