package com.hcl.ecommerce.Controller.User.CRUD;

import com.hcl.ecommerce.Dao.UserDao;
import com.hcl.ecommerce.Model.DbCon;
import com.hcl.ecommerce.Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "InsertUser", value = "/insert-user")
public class InsertUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String usertype = request.getParameter("usertype");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String lastname = request.getParameter("lastname");
        String phonenumber = request.getParameter("phonenumber");
        UserDao udao = null;
        User user = new User(firstname, email, password, usertype, address, city, zip, state, country, lastname, phonenumber);
        try {
            udao = new UserDao(DbCon.getConnection());
            udao.insertUser(user);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("UserList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
