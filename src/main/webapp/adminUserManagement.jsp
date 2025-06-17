<%--
    Document   : adminUserManagement
    Created on : Jun 11, 2025, 1:29:43 AM
    Author     : Nadiah Binti Abdul Latif
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL Core taglib for c:forEach, c:out --%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Users - Admin - MathKids Quiz</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
    </head>
    <body>
        <%-- Include the common header for consistent navigation and styling --%>
        <jsp:include page="header.jsp" />

        <div class="container">
            <h2>User Account Maintenance</h2>

            <%-- Display error or success messages from the request scope --%>
            <c:if test="${not empty errorMessage}">
                <p class="message error">${errorMessage}</p>
            </c:if>
            <c:if test="${not empty successMessage}">
                <p class="message success">${successMessage}</p>
            </c:if>

            <div class="table-responsive"> <%-- Wrapper for responsive table behavior --%>
                <table class="data-table"> <%-- Apply data-table class for styling --%>
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
                        <c:forEach var="user" items="${userList}">
                            <tr>
                                <td><c:out value="${user.id}"/></td>
                                <td><c:out value="${user.username}"/></td>
                                <td><c:out value="${user.email}"/></td>
                                <td><c:out value="${user.role}"/></td>
                                <td class="actions">
                                    <%-- Delete button with styling and confirmation --%>
                                    <a href="<%= request.getContextPath() %>/admin/users?action=delete&id=<c:out value="${user.id}"/>"
                                       class="button button-danger" <%-- Apply button-danger class for styling --%>
                                       onclick="return confirm('Are you sure you want to delete user: <c:out value="${user.username}"/>?');">
                                       <i class="fas fa-trash"></i> Delete <%-- Font Awesome icon for trash --%>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty userList}">
                            <tr>
                                <td colspan="5">No users found.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div> <%-- End of table-responsive --%>
        </div> <%-- End of container --%>

        <%-- Include the common footer --%>
        <jsp:include page="footer.jsp" />
    </body>
</html>