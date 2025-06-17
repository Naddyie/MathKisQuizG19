<%-- 
    Document   : user_list
    Created on : Jun 12, 2025, 1:44:22 AM
    Author     : User
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Users - MathKids Quiz Admin</title>
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
        .actions a {
            margin-right: 10px;
            text-decoration: none;
            color: #dc3545; /* Red for delete */
        }
        .actions a:hover {
            text-decoration: underline;
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
        <h2>Manage Users</h2>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${requestScope.listUsers}">
                    <tr>
                        <td><c:out value="${user.id}" /></td>
                        <td><c:out value="${user.username}" /></td>
                        <td><c:out value="${user.email}" /></td>
                        <td><c:out value="${user.role}" /></td>
                        <td class="actions">
                            <%-- Admin should not delete their own account easily or the only admin account --%>
                            <c:if test="${user.id != sessionScope.currentUser.id}">
                                <a href="${pageContext.request.contextPath}/deleteUser?id=<c:out value='${user.id}' />" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                            </c:if>
                            <c:if test="${user.id == sessionScope.currentUser.id}">
                                <span style="color: #6c757d;">(Your Account)</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p class="back-link"><a href="${pageContext.request.contextPath}/dashboard">Back to Dashboard</a></p>
    </div>
</body>
</html>