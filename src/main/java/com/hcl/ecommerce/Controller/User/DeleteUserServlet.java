package com.hcl.ecommerce.Controller.User;

import com.hcl.ecommerce.Dao.UserDao;
import com.hcl.ecommerce.Model.DbCon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteUserServlet", value = "/delete-user")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao udao = null;
        try {
            udao = new UserDao(DbCon.getConnection());
            int id = Integer.parseInt(request.getParameter("id"));
            udao.deleteUser(id);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("UserList.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
