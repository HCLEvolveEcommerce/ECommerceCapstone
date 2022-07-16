package com.hcl.ecommerce.Controller.Product.CRUD;

import com.hcl.ecommerce.Dao.ProductDao;
import com.hcl.ecommerce.Model.DbCon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteProductServlet", value = "/delete-product")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = null;
        try {
            productDao = new ProductDao(DbCon.getConnection());
            int id = Integer.parseInt(request.getParameter("id"));
            productDao.deleteProducts(id); //code this
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("ProductList.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
