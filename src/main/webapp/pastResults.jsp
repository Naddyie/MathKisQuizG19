<%-- 
    Document   : pastResults
    Created on : Jun 12, 2025, 3:04:15 AM
    Author     : User
--%>

<%-- src/main/webapp/quiz/pastResults.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%-- For formatting dates --%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Past Quiz Results</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h2>My Past Quiz Results</h2>

        <c:if test="${not empty sessionScope.message}">
            <div class="message">${sessionScope.message}</div>
            <c:remove var="message" scope="session"/>
        </c:if>

        <div class="table-responsive">
            <table class="data-table">
                <thead>
                    <tr>
                        <th>Quiz ID</th>
                        <th>Score</th>
                        <th>Total Questions</th>
                        <th>Date Taken</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="result" items="${requestScope.userResults}">
                        <tr>
                            <td><c:out value="${result.id}" /></td>
                            <td><c:out value="${result.score}" /></td>
                            <td><c:out value="${result.totalQuestions}" /></td>
                            <td><fmt:formatDate value="${result.quizDate}" pattern="yyyy-MM-dd HH:mm:ss" timeZone="Asia/Kuala_Lumpur" /></td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty requestScope.userResults}">
                        <tr>
                            <td colspan="4">No past quiz results found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>

        <p><a href="<%= request.getContextPath() %>/dashboard" class="button button-secondary">Back to Dashboard</a></p>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
