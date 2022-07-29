package com.hcl.ecommerce.Controller.Email;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CompletedOrdServlet", value = "/order-email")
public class CompletedOrdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        System.out.println("im called one");
        System.out.println("Im called 2");

        // reads form fields
        String header = "Thanks for shopping with us! ";
        String message = "Your order details are:  ";
        String toAddress = "julian.gaston@hcl.com"; //get email recipient

        // comment to commit
        try {
            SendEmail.sendEmail(toAddress, message, header);
            System.out.println("before auth");
            if(request.getSession().getAttribute("auth")!=null) {
                response.sendRedirect("AccountPage.jsp");
            }
            else {
                response.sendRedirect("index.jsp");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
