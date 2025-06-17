package com.mathkidsquiz.controller;

import com.mathkidsquiz.model.User; // Assuming you have your User model here
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dashboard") // This servlet handles the /dashboard URL for all users
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // Get existing session, don't create new

        // --- User Authentication Check ---
        // If no session or no user in session, redirect to login page
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get the current user from the session
        User currentUser = (User) session.getAttribute("currentUser");

        // Pass user's username and role to the JSP
        request.setAttribute("username", currentUser.getUsername());
        request.setAttribute("userRole", currentUser.getRole()); // This is key for conditional rendering

        // Forward to the single dashboard JSP
        // This JSP will then decide what to show based on 'userRole'
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // For a dashboard, doGet is usually sufficient.
        // If there were forms on the dashboard, this method would process them.
        doGet(request, response);
    }
}