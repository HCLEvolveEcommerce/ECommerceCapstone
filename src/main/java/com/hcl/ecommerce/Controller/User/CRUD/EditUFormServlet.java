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

@WebServlet(name = "ShowEditUserForm", value = "/editU-form")
public class EditUFormServlet extends HttpServlet {
    private UserDao userDao;
    private User user;
    public EditUFormServlet(UserDao uDao, User user) {
        userDao = uDao;
        this.user = user;
    }
    public EditUFormServlet() {
        user = new User();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("yo?");
            if (user != null) {
                request.getSession().setAttribute("auth", user);
                    if (Objects.equals(user.getUsertype(), "Admin")) {
                        System.out.println("yo??");
                        userDao = new UserDao(DbCon.getConnection());
                        int id = Integer.parseInt(request.getParameter("id"));
                        User existingUser = userDao.selectUser(id);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateUserForm.jsp"); //created later
                        request.setAttribute("user", existingUser);
                        dispatcher.forward(request, response);
                    } else {
                        userDao = new UserDao(DbCon.getConnection());
                        int id = Integer.parseInt(request.getParameter("id"));
                        User existingUser = userDao.selectUser(id);
                        System.out.println("yo????");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("ClientUpdateForm.jsp"); //created later
                        request.setAttribute("user", existingUser);
                        dispatcher.forward(request, response);
                    }
                }

            } catch(ClassNotFoundException | SQLException e){
                throw new RuntimeException(e);
            }
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
