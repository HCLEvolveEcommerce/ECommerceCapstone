package com.hcl.ecommerce.Controller.Product.CRUD;

import com.hcl.ecommerce.Dao.ProductDao;
import com.hcl.ecommerce.Model.DbCon;
import com.hcl.ecommerce.Model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "InsertProductServlet", value = "/insert-product")
public class InsertProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price")); //check back on this
        String image = request.getParameter("image");
        ProductDao productDao = null;
        Product product = new Product(name, category , price, image);
        try {
            productDao = new ProductDao(DbCon.getConnection());
            productDao.insertProduct(product);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("ProductList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}