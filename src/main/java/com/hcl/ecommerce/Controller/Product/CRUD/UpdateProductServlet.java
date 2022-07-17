package com.hcl.ecommerce.Controller.Product.CRUD;

import com.hcl.ecommerce.Dao.ProductDao;
import com.hcl.ecommerce.Dao.UserDao;
import com.hcl.ecommerce.Model.DbCon;
import com.hcl.ecommerce.Model.Product;
import com.hcl.ecommerce.Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateProductServlet", value = "/update-product")
public class UpdateProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Am I touched");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("post parse");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        Double price = Double.valueOf(request.getParameter("price")); //idk about this
        String image = request.getParameter("image");
        ProductDao productDao = null;
        Product product = new Product(id, name, category, price, image);
        System.out.println("before try");

        try {
            productDao = new ProductDao(DbCon.getConnection());
            productDao.updateProduct(product);
            System.out.println("after try");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("after catch");
        response.sendRedirect("ProductList.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
