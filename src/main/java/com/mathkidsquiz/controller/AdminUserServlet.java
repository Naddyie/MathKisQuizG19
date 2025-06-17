package com.mathkidsquiz.controller;

import com.mathkidsquiz.dao.UserDAO;
import com.mathkidsquiz.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet("/admin/users")
public class AdminUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is an admin
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        User currentUser = (User) session.getAttribute("currentUser");
        if (!"admin".equals(currentUser.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Admins only.");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Default action
        }

        switch (action) {
            case "delete":
                deleteUser(request, response);
                break;
            case "list":
            default:
                listUsers(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // For simplicity, POST can also redirect to doGet or handle specific updates/adds
        doGet(request, response); // Redirects POST to GET for list and delete, which is not ideal for REST principles but simplifies for now.
                                 // For actual updates/adds, you'd have separate doPost methods.
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> userList = userDAO.getAllUsers();
        request.setAttribute("userList", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminUserManagement.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("id"));
            if (userDAO.deleteUser(userId)) {
                request.setAttribute("successMessage", "User deleted successfully.");
            } else {
                request.setAttribute("errorMessage", "Failed to delete user.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid user ID for deletion.");
        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
            request.setAttribute("errorMessage", "An unexpected error occurred during user deletion.");
        }
        listUsers(request, response); // Redirect back to the user list
    }
}