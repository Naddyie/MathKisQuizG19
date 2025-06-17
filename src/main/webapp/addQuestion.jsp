<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Question</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <jsp:include page="/header.jsp" />

    <div class="container">
        <h2>Add New Quiz Question</h2>

        <c:if test="${not empty sessionScope.message}">
            <div class="success-message">${sessionScope.message}</div>
            <c:remove var="message" scope="session"/>
        </c:if>

        <form action="${pageContext.request.contextPath}/admin/questions?action=insert" method="post" class="form-container">
            <div class="form-group">
                <label for="questionText">Question Text:</label>
                <textarea id="questionText" name="questionText" rows="4" required></textarea>
            </div>

            <div class="form-group">
                <label for="optionA">Option A:</label>
                <input type="text" id="optionA" name="optionA" required>
            </div>
            <div class="form-group">
                <label for="optionB">Option B:</label>
                <input type="text" id="optionB" name="optionB" required>
            </div>
            <div class="form-group">
                <label for="optionC">Option C:</label>
                <input type="text" id="optionC" name="optionC" required>
            </div>
            <div class="form-group">
                <label for="optionD">Option D:</label>
                <input type="text" id="optionD" name="optionD" required>
            </div>
            <div class="form-group">
                <label for="correctOption">Correct Option (A, B, C, or D):</label>
                <input type="text" id="correctOption" name="correctOption" maxlength="1" pattern="[ABCDabcd]" title="Must be A, B, C, or D" required>
            </div>
            <div class="form-group">
                <label for="difficulty">Difficulty:</label>
                <select id="difficulty" name="difficulty" required>
                    <option value="easy">Easy</option>
                    <option value="medium">Medium</option>
                    <option value="hard">Hard</option>
                </select>
            </div>

            <button type="submit" class="button-primary">Add Question</button>
            <a href="${pageContext.request.contextPath}/admin/questions?action=list" class="button-danger" style="margin-left: 10px;">Cancel</a>
        </form>
    </div>

    <jsp:include page="/footer.jsp" />
</body>
</html>
