<%-- 
    Document   : editQuestion
    Created on : Jun 12, 2025, 2:55:35 AM
    Author     : User
--%>

<%-- src/main/webapp/admin/editQuestion.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Question</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h2>Edit Quiz Question</h2>

        <c:if test="${not empty sessionScope.message}">
            <div class="message">${sessionScope.message}</div>
            <c:remove var="message" scope="session"/>
        </c:if>

        <c:set var="question" value="${requestScope.question}" /> <%-- The question object passed from servlet --%>
        <a href="editQuestion.jsp"></a>

        <form action="<%= request.getContextPath() %>/admin/questions?action=update" method="post" class="form-container">
            <input type="hidden" name="id" value="${questions.id}" />">

            <div class="form-group">
                <label for="questionText">Question Text:</label>
                <textarea id="questionText" name="questionText" rows="4" required><c:out value="${question.questionText}" /></textarea>
            </div>
            <div class="form-group">
                <label for="optionA">Option A:</label>
                <input type="text" id="optionA" name="optionA" value="<c:out value="${question.optionA}" />" required>
            </div>
            <div class="form-group">
                <label for="optionB">Option B:</label>
                <input type="text" id="optionB" name="optionB" value="<c:out value="${question.optionB}" />" required>
            </div>
            <div class="form-group">
                <label for="optionC">Option C:</label>
                <input type="text" id="optionC" name="optionC" value="<c:out value="${question.optionC}" />" required>
            </div>
            <div class="form-group">
                <label for="optionD">Option D:</label>
                <input type="text" id="optionD" name="optionD" value="<c:out value="${question.optionD}" />" required>
            </div>
            <div class="form-group">
                <label for="correctOption">Correct Option (A, B, C, or D):</label>
                <input type="text" id="correctOption" name="correctOption" maxlength="1" pattern="[ABCDabcd]" title="Must be A, B, C, or D" value="<c:out value="${question.correctOption}" />" required>
            </div>
            <div class="form-group">
                <label for="difficulty">Difficulty:</label>
                <select id="difficulty" name="difficulty" required>
                    <option value="easy" <c:if test="${question.difficulty == 'easy'}">selected</c:if>>Easy</option>
                    <option value="medium" <c:if test="${question.difficulty == 'medium'}">selected</c:if>>Medium</option>
                    <option value="hard" <c:if test="${question.difficulty == 'hard'}">selected</c:if>>Hard</option>
                </select>
            </div>
            <button type="submit" class="button button-primary">Update Question</button>
            <a href="<%= request.getContextPath() %>/admin/questions?action=list" class="button button-secondary">Cancel</a>
        </form>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
