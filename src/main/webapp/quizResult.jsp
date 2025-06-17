<%-- 
    Document   : quizResult
    Created on : Jun 12, 2025, 2:02:10 AM
    Author     : Nadhirah
--%>

<%-- src/main/webapp/quiz/quizResult.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz Results</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <jsp:include page="/header.jsp" />

    <div class="container">
        <h2>Your Quiz Results</h2>

        <div class="result-summary">
            <h3>Score: <c:out value="${requestScope.score}" /> / <c:out value="${requestScope.totalQuestions}" /></h3>
            <p>Congratulations on completing the quiz!</p>
        </div>

        <h3>Review Your Answers:</h3>
        <div class="quiz-review">
            <c:forEach var="question" items="${requestScope.quizQuestions}" varStatus="loop">
                <div class="question-block">
                    <h4>Question ${loop.count}: <c:out value="${question.questionText}" /></h4>
                    <ul class="options-list">
                        
                        <li>
                            <c:choose>
                                <c:when test="${question.correctOption eq 'A'}">
                                    <span class="correct-answer">A)</span>
                                </c:when>
                                <c:otherwise>
                                    <span>A)</span>
                                </c:otherwise>
                            </c:choose>
                            <c:out value="${question.optionA}" />
                            <c:if test="${requestScope.userAnswers[loop.index] eq 'A'}">
                                <span class="user-selected">(Your Answer)</span>
                            </c:if>
                        </li>

                        <li>
                            <c:choose>
                                <c:when test="${question.correctOption eq 'B'}">
                                    <span class="correct-answer">B)</span>
                                </c:when>
                                <c:otherwise>
                                    <span>B)</span>
                                </c:otherwise>
                            </c:choose>
                            <c:out value="${question.optionB}" />
                            <c:if test="${requestScope.userAnswers[loop.index] eq 'B'}">
                                <span class="user-selected">(Your Answer)</span>
                            </c:if>
                        </li>
                        
                        <li>
                            <c:choose>
                                <c:when test="${question.correctOption eq 'C'}">
                                    <span class="correct-answer">C)</span>
                                </c:when>
                                <c:otherwise>
                                    <span>C)</span>
                                </c:otherwise>
                            </c:choose>
                            <c:out value="${question.optionC}" />
                            <c:if test="${requestScope.userAnswers[loop.index] eq 'C'}">
                                <span class="user-selected">(Your Answer)</span>
                            </c:if>
                        </li>
                        
                        <li>
                            <c:choose>
                                <c:when test="${question.correctOption eq 'D'}">
                                    <span class="correct-answer">D)</span>
                                </c:when>
                                <c:otherwise>
                                    <span>D)</span>
                                </c:otherwise>
                            </c:choose>
                            <c:out value="${question.optionD}" />
                            <c:if test="${requestScope.userAnswers[loop.index] eq 'D'}">
                                <span class="user-selected">(Your Answer)</span>
                            </c:if>
                        </li>
                        
                        <!-- comment <li class="<c:if test="${question.correctOption eq 'A'}">correct-answer</c:if>">
                            A) <c:out value="${question.optionA}" />
                            <c:if test="${requestScope.userAnswers[loop.index] eq 'A'}"> <span class="user-selected">(Your Answer)</span></c:if>
                        </li>
                        <li class="<c:if test="${question.correctOption eq 'B'}">correct-answer</c:if>">
                            B) <c:out value="${question.optionB}" />
                            <c:if test="${requestScope.userAnswers[loop.index] eq 'B'}"> <span class="user-selected">(Your Answer)</span></c:if>
                        </li>
                        <li class="<c:if test="${question.correctOption eq 'C'}">correct-answer</c:if>">
                            C) <c:out value="${question.optionC}" />
                            <c:if test="${requestScope.userAnswers[loop.index] eq 'C'}"> <span class="user-selected">(Your Answer)</span></c:if>
                        </li>
                        <li class="<c:if test="${question.correctOption eq 'D'}">correct-answer</c:if>">
                            D) <c:out value="${question.optionD}" />
                            <c:if test="${requestScope.userAnswers[loop.index] eq 'D'}"> <span class="user-selected">(Your Answer)</span></c:if>
                        </li>-->
                    </ul>
                </div>
            </c:forEach>
        </div>

        <p><a href="<%= request.getContextPath() %>/dashboard" class="button button-secondary">Back to Dashboard</a></p>
        <p><a href="<%= request.getContextPath() %>/quiz" class="button button-primary">Retake Quiz</a></p>
    </div>

    <jsp:include page="/footer.jsp" />
</body>
</html>