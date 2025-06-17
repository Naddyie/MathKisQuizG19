// src/main/java/com/mathkidsquiz/controller/AdminQuestionServlet.java
package com.mathkidsquiz.controller;

import com.mathkidsquiz.dao.QuestionDAO;
import com.mathkidsquiz.model.Question;
import com.mathkidsquiz.model.User; // Required to check admin role

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin/questions") // Maps this servlet to /admin/questions URL
public class AdminQuestionServlet extends HttpServlet {

    private QuestionDAO questionDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        questionDAO = new QuestionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // --- Admin Role Check ---
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // Not logged in, redirect to login
            return;
        }
        User currentUser = (User) session.getAttribute("currentUser");
        if (!"admin".equals(currentUser.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: You must be an administrator to manage questions.");
            return; // Not an admin
        }
        // --- End Admin Role Check ---

        String action = request.getParameter("action"); // Get action from URL parameter

        if (action == null) {
            action = "list"; // Default action is to list all questions
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "insert": // Insert operations typically handle POST requests, but for simplicity,
                           // if a GET request with action=insert comes, it will also be handled.
                           // For better practice, insert should be in doPost.
                insertQuestion(request, response);
                break;
            case "delete":
                deleteQuestion(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update": // Update operations typically handle POST requests, same as insert.
                           // For better practice, update should be in doPost.
                updateQuestion(request, response);
                break;
            case "list":
            default:
                listQuestions(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // It's generally better practice to explicitly handle POST actions here
        // instead of just calling doGet, as GET requests should be idempotent
        // (not change server state), while POST requests are for changes.
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Default action if no action parameter is provided
        }

        switch (action) {
            case "insert":
                insertQuestion(request, response);
                break;
            case "update":
                updateQuestion(request, response);
                break;
            // You might not need delete in doPost if you're using GET for it,
            // but for safety, consider making delete operations use POST as well.
            default:
                // For other actions (like "list", "new", "edit", "delete"),
                // you might still want to call doGet or redirect if they are idempotent.
                doGet(request, response);
                break;
        }
    }


    private void listQuestions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Question> listQuestions = questionDAO.getAllQuestions();
        request.setAttribute("questionList", listQuestions);
        // Corrected path for adminQuestionManagement.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminQuestionManagement.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Corrected path for addQuestion.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addQuestion.jsp");
        dispatcher.forward(request, response);
    }

    private void insertQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionText = request.getParameter("questionText");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        // Ensure correctOption is handled safely, e.g., default value or validation
        //char correctOption = '\0'; // Default value
        String correctOption = request.getParameter("correctOption");
        String correctOptionParam = request.getParameter("correctOption");
        /*if (correctOptionParam != null && !correctOptionParam.isEmpty()) {
            correctOption = correctOptionParam.toUpperCase().charAt(0); // Convert to uppercase char
        } else {
             // Handle error: correctOption is missing.
             // For example, set an error message and redirect back to the form
             request.getSession().setAttribute("errorMessage", "Correct option cannot be empty.");
             response.sendRedirect(request.getContextPath() + "/admin/questions?action=new");
             return; // Stop execution
        }*/
        if (correctOption == null || correctOption.trim().isEmpty()) {
            request.getSession().setAttribute("errorMessage", "Correct option cannot be empty.");
            response.sendRedirect(request.getContextPath() + "/admin/questions?action=new");
            return;
        }
        correctOption = correctOption.toUpperCase(); // Ensure it's A/B/C/D

        String difficulty = request.getParameter("difficulty");

        
        Question newQuestion = new Question(questionText, optionA, optionB, optionC, optionD, correctOption, difficulty);
        if (questionDAO.addQuestion(newQuestion)) {
            request.getSession().setAttribute("successMessage", "Question added successfully!");
        } else {
            request.getSession().setAttribute("errorMessage", "Failed to add question.");
        }
        response.sendRedirect(request.getContextPath() + "/admin/questions?action=list");
    }

    private void deleteQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (questionDAO.deleteQuestion(id)) {
            request.getSession().setAttribute("successMessage", "Question deleted successfully!");
        } else {
            request.getSession().setAttribute("errorMessage", "Failed to delete question.");
        }
        response.sendRedirect(request.getContextPath() + "/admin/questions?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Question existingQuestion = questionDAO.getQuestionById(id);
        request.setAttribute("questions", existingQuestion);
        // Corrected path for editQuestion.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editQuestion.jsp");
        dispatcher.forward(request, response);
    }

    private void updateQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.trim().isEmpty()) {
            request.getSession().setAttribute("errorMessage", "Question ID is missing or invalid.");
            response.sendRedirect(request.getContextPath() + "/admin/questions?action=list");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "Question ID must be a valid number.");
            response.sendRedirect(request.getContextPath() + "/admin/questions?action=list");
            return;
        }

        String questionText = request.getParameter("questionText");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");

        /*char correctOption = '\0';
        String correctOptionParam = request.getParameter("correctOption");
        if (correctOptionParam != null && !correctOptionParam.isEmpty()) {
            correctOption = correctOptionParam.toUpperCase().charAt(0);
        } else {
            request.getSession().setAttribute("errorMessage", "Correct option cannot be empty.");
            response.sendRedirect(request.getContextPath() + "/admin/questions?action=edit&id=" + id);
            return;
        }*/
        String correctOption = request.getParameter("correctOption");
        if (correctOption == null || correctOption.trim().isEmpty()) {
            request.getSession().setAttribute("errorMessage", "Correct option cannot be empty.");
            response.sendRedirect(request.getContextPath() + "/admin/questions?action=edit&id=" + id);
            return;
        }
        correctOption = correctOption.toUpperCase(); // A/B/C/D


        String difficulty = request.getParameter("difficulty");

        Question question = new Question(id, questionText, optionA, optionB, optionC, optionD, correctOption, difficulty);
        if (questionDAO.updateQuestion(question)) {
            request.getSession().setAttribute("successMessage", "Question updated successfully!");
        } else {
            request.getSession().setAttribute("errorMessage", "Failed to update question.");
        }
        response.sendRedirect(request.getContextPath() + "/admin/questions?action=list");
    }

}