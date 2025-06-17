<%--
    Document   : adminQuestionManagement
    Created on : Jun 12, 2025
    Author     : Your Name (e.g., Nur Munirah Binti Azman, as per Module 2 responsibility)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Questions - Admin - MathKids Quiz</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>
    <body>
        <%-- CORRECTED PATH for header.jsp --%>
        <jsp:include page="header.jsp" />

        <div class="container">
            <h2>Quiz Question Management</h2>

            <c:if test="${not empty errorMessage}">
                <p class="message error">${errorMessage}</p>
            </c:if>
            <c:if test="${not empty successMessage}">
                <p class="message success">${successMessage}</p>
            </c:if>

            <div class="add-button-container" style="text-align: right; margin-bottom: 20px;">
                <a href="${pageContext.request.contextPath}/admin/questions?action=new" class="button-primary">+ Add New Question</a>
            </div>

            <div class="table-responsive">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Question Text</th>
                            <th>Option A</th>
                            <th>Option B</th>
                            <th>Option C</th>
                            <th>Option D</th>
                            <th>Correct Answer</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="question" items="${questionList}">
                            <tr>
                                <td><c:out value="${question.id}"/></td>
                                <td><c:out value="${question.questionText}"/></td>
                                <td><c:out value="${question.optionA}"/></td>
                                <td><c:out value="${question.optionB}"/></td>
                                <td><c:out value="${question.optionC}"/></td>
                                <td><c:out value="${question.optionD}"/></td>
                                <td><c:out value="${question.correctOption}"/></td>
                                
                                <td class="actions">
                                    <a href="${pageContext.request.contextPath}/admin/questions?action=edit&id=${question.id}"
                                       class="button button-primary">
                                        <i class="fas fa-edit"></i> Edit
                                    </a>
                                    <a href="<%= request.getContextPath() %>/admin/questions?action=delete&id=<c:out value="${question.id}"/>"
                                       class="button button-danger" onclick="return confirm('Are you sure you want to delete this question (ID: <c:out value="${question.id}"/>)?');">
                                        <i class="fas fa-trash"></i> Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty questionList}">
                            <tr>
                                <td colspan="8">No quiz questions found.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>

        <%-- CORRECTED PATH for footer.jsp --%>
        <jsp:include page="footer.jsp" />
    </body>
</html>