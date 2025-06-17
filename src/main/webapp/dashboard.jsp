<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - MathKids Quiz</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container dashboard-container">
        
        <h2>Welcome, <c:out value="${sessionScope.currentUser.username}" />!</h2>

        <p>This is your personalized dashboard for the MathKids Quiz System.</p>

        <div class="dashboard-links">
            <div class="card">
                <h3><i class="fas fa-play-circle"></i> Take a Quiz</h3>
                <p>Ready to test your math skills? Start a new quiz!</p>
                <a href="<%= request.getContextPath() %>/quiz?action=start" class="button button-primary"><i class="fas fa-play"></i> Start Quiz</a>
            </div>

            <div class="card">
                <h3><i class="fas fa-chart-line"></i> View My Results</h3>
                <p>Check your past performance and track your progress.</p>
                <a href="<%= request.getContextPath() %>/user/quiz_results" class="button button-info">
                    <i class="fas fa-list-alt"></i> My Results
                </a>

                <!-- <a href="<%= request.getContextPath() %>/results?action=myresults" class="button button-info"><i class="fas fa-list-alt"></i> My Results</a> -->
            </div>
        </div>

        <c:if test="${not empty sessionScope.successMessage}">
            <p class="message success">${sessionScope.successMessage}</p>
            <c:remove var="successMessage" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.errorMessage}">
            <p class="message error">${sessionScope.errorMessage}</p>
            <c:remove var="errorMessage" scope="session"/>
        </c:if>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>