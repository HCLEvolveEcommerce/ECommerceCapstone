package com.hcl.ecommerce.Controller.Email;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name ="/SendEmailServlet", value= "/email-signup")
public class SignupEmailServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        System.out.println("im called one");
        System.out.println("Im called 2");

        // reads form fields
        String toAddress = request.getParameter("email");
        String header = "Welcome to Evolve";
        String message = "Congratulations on creating your account. Here at Evolve Ecommerce Solutions we care about any inquiries you might have. Please feel free to reach me at dsikes313@gmail.com";


        try {
            SendEmail.sendEmail(toAddress, message, header);
            response.sendRedirect("login.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}