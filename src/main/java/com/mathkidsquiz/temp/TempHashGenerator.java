package com.mathkidsquiz.temp;

import com.mathkidsquiz.dao.UserDAO;

/**
 *
 * @author User
 */
public class TempHashGenerator {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        String plainTextPass = "your_chosen_admin_password"; // <--- PUT YOUR DESIRED PLAIN TEXT PASSWORD HERE
        String hashedPass = userDAO.hashPassword(plainTextPass);
        System.out.println("Plain text: " + plainTextPass);
        System.out.println("Generated Hash: " + hashedPass);
    }
}
