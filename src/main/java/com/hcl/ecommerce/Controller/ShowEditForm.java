package com.hcl.ecommerce.Controller;

import com.hcl.ecommerce.Dao.UserDao;
import com.hcl.ecommerce.Model.DbCon;
import com.hcl.ecommerce.Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ShowEditForm", value = "/edit-form")
public class ShowEditForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = null;
        try {
            userDao= new UserDao(DbCon.getConnection());
            int id = Integer.parseInt(request.getParameter("id"));
            User existingUser = userDao.selectUser(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateUserForm.jsp"); //created later
            request.setAttribute("user", existingUser);
            dispatcher.forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
