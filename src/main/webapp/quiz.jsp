<%-- 
    Document   : quiz
    Created on : Jun 12, 2025, 2:00:26 AM
    Author     : Nadhirah
--%>

<%-- src/main/webapp/quiz/quiz.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MathKids Quiz</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h2>MathKids Quiz</h2>

        <c:if test="${not empty requestScope.message}">
            <div class="message">${requestScope.message}</div>
        </c:if>

        <form action="<%= request.getContextPath() %>/quiz" method="post" class="quiz-form">
            <c:choose>
                <c:when test="${not empty requestScope.questions}">
                    <c:forEach var="question" items="${requestScope.questions}" varStatus="loop">
                        <div class="question-block">
                            <h3>Question ${loop.count}: <c:out value="${question.questionText}" /></h3>
                            <input type="hidden" name="question_id_${loop.count}" value="<c:out value="${question.id}"/>">
                            <ul class="options-list">
                                <li>
                                    <input type="radio" id="q${question.id}_optionA" name="question_<c:out value='${question.id}'/>_answer" value="A">
                                    <label for="q${question.id}_optionA">A) <c:out value="${question.optionA}" /></label>
                                </li>
                                <li>
                                    <input type="radio" id="q${question.id}_optionB" name="question_<c:out value='${question.id}'/>_answer" value="B">
                                    <label for="q${question.id}_optionB">B) <c:out value="${question.optionB}" /></label>
                                </li>
                                <li>
                                    <input type="radio" id="q${question.id}_optionC" name="question_<c:out value='${question.id}'/>_answer" value="C">
                                    <label for="q${question.id}_optionC">C) <c:out value="${question.optionC}" /></label>
                                </li>
                                <li>
                                    <input type="radio" id="q${question.id}_optionD" name="question_<c:out value='${question.id}'/>_answer" value="D">
                                    <label for="q${question.id}_optionD">D) <c:out value="${question.optionD}" /></label>
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                    <button type="submit" class="button button-primary">Submit Quiz</button>
                </c:when>
                <c:otherwise>
                    <p>No questions available for the quiz at the moment.</p>
                </c:otherwise>
            </c:choose>
        </form>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>