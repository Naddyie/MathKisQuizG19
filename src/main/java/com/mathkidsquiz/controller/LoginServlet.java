package com.mathkidsquiz.controller;

import com.mathkidsquiz.dao.UserDAO;
import com.mathkidsquiz.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Just forward to the login JSP for GET requests
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.loginUser(username, password);

        if (user != null) {
            // Login successful
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user); // Store user object in session
            session.setMaxInactiveInterval(30 * 60); // Session timeout in seconds (30 minutes)

            if ("admin".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/adminDashboard.jsp"); // Redirect to admin dashboard
            } else {
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp"); // Redirect to student dashboard
            }
        } else {
            // Login failed
            request.setAttribute("errorMessage", "Invalid username or password.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}