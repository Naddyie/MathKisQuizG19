<%-- 
    Document   : login
    Created on : Jun 11, 2025, 1:26:35 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login - MathKids Quiz</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <h2>Login to MathKids Quiz</h2>
            <form action="<%= request.getContextPath() %>/login" method="post">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
                <% } %>
                <% if (request.getAttribute("successMessage") != null) { %>
                    <p class="success-message"><%= request.getAttribute("successMessage") %></p>
                <% } %>
                <button type="submit" class="button button-primary">Login</button>
            </form>
            <p>Don't have an account? <a href="<%= request.getContextPath() %>/register">Register here</a></p>
        </div>
    </body>
</html>
