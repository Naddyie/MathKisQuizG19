<%--
    Document   : header
    Created on : Jun 12, 2025, 3:17:06 AM
    Author     : User
--%>

<%-- src/main/webapp/WEB-INF/views/header.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MathKids Quiz</title> <%-- Default title, can be overridden by specific JSPs --%>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <%-- Add Font Awesome for icons if not already linked in your style.css --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <header class="main-header">
        <div class="header-container">
            <div class="logo">
                <a href="<%= request.getContextPath() %>/">MathKids Quiz</a>
            </div>
            <nav class="main-nav">
                <ul>
                    <c:choose>
                        <%-- If user is NOT logged in --%>
                        <c:when test="${sessionScope.currentUser == null}">
                            <li><a href="<%= request.getContextPath() %>/login">Login</a></li>
                            <li><a href="<%= request.getContextPath() %>/register">Register</a></li>
                        </c:when>
                        <%-- If user IS logged in --%>
                        <c:otherwise>
                            <%-- Admin Links --%>
                            <c:if test="${sessionScope.currentUser.role == 'admin'}">
                                <li><a href="<%= request.getContextPath() %>/adminDashboard.jsp"><i class="fas fa-tachometer-alt"></i> Admin Dashboard</a></li>
                                <li><a href="<%= request.getContextPath() %>/admin/users"><i class="fas fa-users"></i> Manage Users</a></li>
                                <li><a href="<%= request.getContextPath() %>/admin/questions"><i class="fas fa-question-circle"></i> Manage Questions</a></li>
                            </c:if>
                            <%-- Student/General User Links (ONLY for students) --%>
                            <c:if test="${sessionScope.currentUser.role == 'student'}">
                                <li><a href="<%= request.getContextPath() %>/dashboard"><i class="fas fa-home"></i> Home</a></li>
                                <li><a href="<%= request.getContextPath() %>/quiz"><i class="fas fa-play-circle"></i> Take Quiz</a></li>
                                <li><a href="<%= request.getContextPath() %>/user/quiz_results"><i class="fas fa-poll"></i> My Results</a></li>
                            </c:if>
                            <%-- Logout Link (visible to all logged-in users, regardless of role) --%>
                            <li><a href="<%= request.getContextPath() %>/logout"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </div>
    </header>
    <main> <%-- Start the main content area for the page. This tag is closed in footer.jsp --%>