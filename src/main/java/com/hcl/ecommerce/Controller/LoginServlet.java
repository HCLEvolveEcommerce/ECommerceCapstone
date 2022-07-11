package com.hcl.ecommerce.Controller;

import com.hcl.ecommerce.Dao.UserDao;
import com.hcl.ecommerce.Model.DbCon;
import com.hcl.ecommerce.Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            UserDao udao = new UserDao(DbCon.getConnection());
            User user = udao.userLogin(email, password);
            if (user != null) {
                request.getSession().setAttribute("auth", user);
                if(Objects.equals(user.getUsertype(), "Admin")) {
                    session.setAttribute("id", user.getId());
                    session.setAttribute("name", user.getName());
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("usertype", user.getUsertype());

                    response.sendRedirect("adminlist.jsp");
                    System.out.println("Logged in as Admin");
                    System.out.println(session.getAttribute("email") + " " + session.getAttribute("usertype"));
                }

                else {
                    session.setAttribute("id", user.getId());
                    session.setAttribute("name", user.getName());
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("usertype", user.getUsertype());
                    response.sendRedirect("index.jsp");
                    System.out.println("Logged in as User");
                    System.out.println(session.getAttribute("email") + " " + session.getAttribute("usertype"));
                }
            } else {
                out.print("Invalid Username or Password");

            }
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }

    }
}
