package com.hcl.ecommerce.Controller.Product;

import com.hcl.ecommerce.Model.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

@WebServlet(name = "RemoveCart", value = "/remove-cart")
public class RemoveCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            int id = Integer.parseInt(request.getParameter("id"));
            Cart cart = new Cart();
            cart.setId(id);
            String action = request.getParameter("action");
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            if (action != null) {
                for (Cart c : cart_list) {
                    for (Cart a : cartList) {
                        if (a.getId() == id) {
                            cartList.remove(a);
                            response.sendRedirect("cart.jsp");
                        }

                    }
                }
            }
        } catch (java.util.ConcurrentModificationException exception) {
            // Catch ConcurrentModificationExceptions.
        }
    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
