package com.hcl.ecommerce.Controller.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/log-out")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(request.getSession().getAttribute("auth")!=null){ //we are getting auth from the first session in login servlet
                request.getSession().removeAttribute("auth");
                request.getSession().invalidate();
                HttpSession session = request.getSession();
                System.out.println(session.getId());
                response.sendRedirect("login.jsp");
                System.out.println("user has logged out ");
            }else {
                response.sendRedirect("index.jsp");
            }

        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}