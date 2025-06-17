// src/main/java/com/mathkidsquiz/controller/QuizServlet.java
package com.mathkidsquiz.controller;

import com.mathkidsquiz.dao.QuestionDAO;
import com.mathkidsquiz.dao.QuizResultDAO; // New DAO for results
import com.mathkidsquiz.model.Question;
import com.mathkidsquiz.model.User;
import com.mathkidsquiz.model.QuizResult; // New model for results

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections; // For shuffling questions
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/quiz") // Maps this servlet to /quiz URL
public class QuizServlet extends HttpServlet {

    private QuestionDAO questionDAO;
    private QuizResultDAO quizResultDAO; // Initialize the new DAO

    @Override
    public void init() throws ServletException {
        super.init();
        questionDAO = new QuestionDAO();
        quizResultDAO = new QuizResultDAO(); // Initialize
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // --- User Authentication Check ---
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // Not logged in
            return;
        }
        User currentUser = (User) session.getAttribute("currentUser");
        if ("admin".equals(currentUser.getRole())) {
            // Admins should not take quizzes, redirect them to their dashboard
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }
        // --- End User Authentication Check ---

        // Get 10 random questions for the quiz
        List<Question> quizQuestions = questionDAO.getRandomQuestions(10); // Fetch 10 questions
        if (quizQuestions.isEmpty()) {
            request.setAttribute("message", "No questions available. Please contact administrator.");
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            return;
        }

        // Store questions in session for grading on POST
        session.setAttribute("quizQuestions", quizQuestions);
        request.setAttribute("questions", quizQuestions); // Also set for JSP display

        request.getRequestDispatcher("/quiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // --- User Authentication Check ---
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        User currentUser = (User) session.getAttribute("currentUser");
        if ("admin".equals(currentUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }
        // --- End User Authentication Check ---

        // Retrieve the original questions from session for grading
        List<Question> quizQuestions = (List<Question>) session.getAttribute("quizQuestions");
        if (quizQuestions == null || quizQuestions.isEmpty()) {
            request.getSession().setAttribute("message", "Quiz session expired or no questions loaded. Please retake the quiz.");
            response.sendRedirect(request.getContextPath() + "/dashboard"); // Redirect to dashboard
            return;
        }

        int score = 0;
        int totalQuestions = quizQuestions.size();
        List<String> userAnswers = new ArrayList<>(); // To store user answers for review if needed

        for (int i = 0; i < totalQuestions; i++) {
            Question q = quizQuestions.get(i);
            // Form field name example: "question_1_answer", "question_2_answer"
            String userAnswer = request.getParameter("question_" + q.getId() + "_answer");

            if (userAnswer != null) {
                userAnswers.add(userAnswer); // Store for potential review later
                if (userAnswer.equalsIgnoreCase(String.valueOf(q.getCorrectOption()))) {
                    score++;
                }
            } else {
                userAnswers.add(""); // User didn't answer this question
            }
        }

        // Record the result in the database
        QuizResult result = new QuizResult(currentUser.getId(), score, totalQuestions);
        if (quizResultDAO.addQuizResult(result)) {
            System.out.println("Quiz result recorded successfully for user: " + currentUser.getUsername());
        } else {
            System.err.println("Failed to record quiz result for user: " + currentUser.getUsername());
        }

        // Store result details for display on quizResult.jsp
        request.setAttribute("score", score);
        request.setAttribute("totalQuestions", totalQuestions);
        request.setAttribute("userAnswers", userAnswers); // If you want to show user's selected answers
        request.setAttribute("quizQuestions", quizQuestions); // To show correct answers

        // Remove questions from session to prevent re-submission or re-grading
        session.removeAttribute("quizQuestions");

        // Forward to the quiz result display page
        request.getRequestDispatcher("/quizResult.jsp").forward(request, response);
    }
}