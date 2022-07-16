package com.hcl.ecommerce.Controller.Product.CRUD;

import com.hcl.ecommerce.Dao.ProductDao;
import com.hcl.ecommerce.Model.DbCon;
import com.hcl.ecommerce.Model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EditPFormServlet", value = "/editP-form") //untested
public class EditPFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = null;
        try {
            productDao= new ProductDao(DbCon.getConnection());
            int id = Integer.parseInt(request.getParameter("id"));
            Product existingProduct = productDao.getSingleProduct(id); //double check this method untested
            RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateProductForm.jsp"); //created later
            request.setAttribute("product", existingProduct);
            dispatcher.forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}