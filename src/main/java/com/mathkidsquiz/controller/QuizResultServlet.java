// src/main/java/com/mathkidsquiz/controller/QuizResultServlet.java
package com.mathkidsquiz.controller;

import com.mathkidsquiz.dao.QuizResultDAO;
import com.mathkidsquiz.model.QuizResult;
import com.mathkidsquiz.model.User;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/quiz_results") // Maps to /user/results
public class QuizResultServlet extends HttpServlet {

    private QuizResultDAO quizResultDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        quizResultDAO = new QuizResultDAO();
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
            // Admins should view all results from an admin panel, not individual user results
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            return;
        }
        // --- End User Authentication Check ---

        List<QuizResult> userResults = quizResultDAO.getQuizResultsByUserId(currentUser.getId());
        request.setAttribute("userResults", userResults);

        request.getRequestDispatcher("/pastResults.jsp").forward(request, response); // Forward to JSP to display results
    }
    // No doPost needed for this servlet as it's just for viewing
}