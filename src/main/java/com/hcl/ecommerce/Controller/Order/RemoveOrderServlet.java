package com.hcl.ecommerce.Controller.Order;

import com.hcl.ecommerce.Dao.OrderDao;
import com.hcl.ecommerce.Dao.ProductDao;
import com.hcl.ecommerce.Model.DbCon;
import com.hcl.ecommerce.Model.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RemoveOrderServlet", value = "/remove-order")
public class RemoveOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDao orderdao = null;
        try {
            orderdao = new OrderDao(DbCon.getConnection());
            int id = Integer.parseInt(request.getParameter("id"));
            orderdao.deleteOrders(id); //code this
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("AccountPage.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
