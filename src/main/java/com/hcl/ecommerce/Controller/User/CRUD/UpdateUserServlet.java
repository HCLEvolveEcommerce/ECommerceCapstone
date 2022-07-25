package com.hcl.ecommerce.Controller.User.CRUD;

import com.hcl.ecommerce.Dao.UserDao;
import com.hcl.ecommerce.Model.DbCon;
import com.hcl.ecommerce.Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(name = "UpdateUserServlet", value = "/update-user" +
        "")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Am I touched");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("post parse");
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
        User user = new User(id, firstname, email, password, usertype, address, city, zip, state, country, lastname, phonenumber);
        System.out.println("before try");
        try {
            udao = new UserDao(DbCon.getConnection());
            udao.updateUser(user);
            System.out.println("after try");
            System.out.println(user.getUsertype());
            System.out.println(user.getFirstname());
        if(Objects.equals(user.getUsertype(), "Admin")) {
            System.out.println("after catch");
            response.sendRedirect("UserList.jsp");
        }
        else{
            response.sendRedirect("AccountPage.jsp");
        }
        } catch (ClassNotFoundException | SQLException e) {














            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

