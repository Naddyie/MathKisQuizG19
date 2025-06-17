/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// src/main/webapp/js/validation.js
function validateRegistrationForm() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm_password").value;
    var username = document.getElementById("username").value;
    var email = document.getElementById("email").value;

    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        return false;
    }

    // Basic password strength check (e.g., minimum 6 characters)
    if (password.length < 6) {
        alert("Password must be at least 6 characters long.");
        return false;
    }

    // Basic email format validation (can be more robust)
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert("Please enter a valid email address.");
        return false;
    }

    // You can add more complex validation here, e.g., username length, special characters.
    return true; // Form will submit if all checks pass
}


