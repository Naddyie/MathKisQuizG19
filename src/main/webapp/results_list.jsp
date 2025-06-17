<%-- 
    Document   : results_list
    Created on : Jun 12, 2025, 1:42:21 AM
    Author     : User
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz Results - MathKids Quiz Admin</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .back-link {
            display: block;
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>All Quiz Results</h2>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>User</th>
                    <th>Score</th>
                    <th>Total Questions</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="result" items="${requestScope.listResults}">
                    <tr>
                        <td><c:out value="${result.id}" /></td>
                        <td><c:out value="${result.username}" /></td> <%-- Display username from JOIN --%>
                        <td><c:out value="${result.score}" /></td>
                        <td><c:out value="${result.totalQuestions}" /></td>
                        <td><fmt:formatDate value="${result.quizDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p class="back-link"><a href="${pageContext.request.contextPath}/dashboard">Back to Dashboard</a></p>
    </div>
</body>
</html>