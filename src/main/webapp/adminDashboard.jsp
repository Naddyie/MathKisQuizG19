<%--
    Document   : adminDashboard
    Created on : Jun 11, 2025, 1:28:51 AM
    Author     : Nadiah Binti Abdul Latif
--%>

<%-- src/main/webapp/admin/adminDashboard.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - MathKids Quiz</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <%-- Ensure Font Awesome is included for icons if not handled by header.jsp --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <%-- CORRECTED PATH for header.jsp --%>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h2>Welcome, <c:out value="${sessionScope.currentUser.username}" />!</h2>

        <p>This is your administrative dashboard. From here, you can manage various aspects of the MathKids Quiz System.</p>

        <div class="dashboard-links">
            <a href="<%= request.getContextPath() %>/admin/users" class="button button-info">Manage Users</a>
            <a href="<%= request.getContextPath() %>/admin/questions" class="button button-info">Manage Quiz Questions</a>

        </div>
    </div>

    <%-- CORRECTED PATH for footer.jsp --%>
    <jsp:include page="footer.jsp" />
</body>
</html>