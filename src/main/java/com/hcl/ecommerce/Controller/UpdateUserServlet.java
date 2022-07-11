package com.hcl.ecommerce.Controller;

import com.hcl.ecommerce.Dao.UserDao;
import com.hcl.ecommerce.Model.DbCon;
import com.hcl.ecommerce.Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EditUserServlet", value = "/update-user" +
        "")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Am I touched");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("post parse");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String usertype = request.getParameter("usertype");
        UserDao udao = null;
        User user = new User(id, name, email, password, usertype);
        System.out.println("before try");
        try {
            udao = new UserDao(DbCon.getConnection());
            udao.updateUser(user);
            System.out.println("after try");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("after catch");
        response.sendRedirect("adminlist.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

