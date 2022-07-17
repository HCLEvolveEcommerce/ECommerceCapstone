package com.hcl.ecommerce.Controller.User;

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
        System.out.println(session.getId());
        response.setContentType("text/html;charset=UTF-8");
        try {
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            UserDao udao = new UserDao(DbCon.getConnection());
            User user = udao.userLogin(email, password);
            if (user != null) {
                request.getSession().setAttribute("auth", user);
                if(Objects.equals(user.getUsertype(), "Admin")) {
                    request.getSession().setAttribute("admin", user);
                    session.setAttribute("id", user.getId());
                    session.setAttribute("firstname", user.getFirstname());
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("usertype", user.getUsertype());
                    session.setAttribute("address", user.getAddress());
                    session.setAttribute("city", user.getCity());
                    session.setAttribute("zip", user.getZip());
                    session.setAttribute("state", user.getState());
                    session.setAttribute("country", user.getCountry());
                    session.setAttribute("lastname", user.getLastname());
                    session.setAttribute("phonenumber", user.getPhonenumber());

                    response.sendRedirect("UserList.jsp");
                    System.out.println("Logged in as Admin");
                    System.out.println(session.getAttribute("email") + " " + session.getAttribute("usertype"));
                }

                else {
                    session.setAttribute("id", user.getId());
                    session.setAttribute("firstname", user.getFirstname());
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("usertype", user.getUsertype());
                    session.setAttribute("address", user.getAddress());
                    session.setAttribute("city", user.getCity());
                    session.setAttribute("zip", user.getZip());
                    session.setAttribute("state", user.getState());
                    session.setAttribute("country", user.getCountry());
                    session.setAttribute("lastname", user.getLastname());
                    session.setAttribute("phonenumber", user.getPhonenumber());
                    response.sendRedirect("index.jsp");
                    System.out.println("Logged in as User");
                    System.out.println(session.getAttribute("email") + " " + session.getAttribute("usertype"));
                }
            } else {
                response.sendRedirect("login.jsp");
                String invalid = "Invalid Login, Please Try Again.";
                request.getSession().setAttribute("invalid", invalid);
                System.out.println(session.getId());

            }
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }

    }
}
