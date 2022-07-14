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

@WebServlet(name = "UserOrderServlet", value = "/user-order")
public class UserOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        System.out.println(session.getId());
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            UserDao udao = new UserDao(DbCon.getConnection());
            User user = udao.userLogin(email, password);
            System.out.println("did I get here ");
            if (user != null) {
                System.out.println("did I get here 2");
                if(Objects.equals(user.getUsertype(), "Admin")) {
                    request.getSession().setAttribute("admin", user);
                    session.setAttribute("id", user.getId());
                    session.setAttribute("name", user.getName());
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("usertype", user.getUsertype());

                    response.sendRedirect("UserList.jsp");
                    System.out.println("Logged in as Admin");
                    System.out.println(session.getAttribute("email") + " " + session.getAttribute("usertype"));
                }

                else {
                    session.setAttribute("id", user.getId());
                    session.setAttribute("name", user.getName());
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("usertype", user.getUsertype());
                    System.out.println("Logged in as User");
                    System.out.println(session.getAttribute("email") + " " + session.getAttribute("usertype"));

                    response.sendRedirect("orders.jsp");
                    System.out.println("you made it" +request.getSession().getAttribute("email"));
                }
            } else {
                response.sendRedirect("CheckoutLogin.jsp");
                String invalid = "Invalid Login, Please Try Again.";
                request.getSession().setAttribute("invalid", invalid);
                System.out.println(session.getId());

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
