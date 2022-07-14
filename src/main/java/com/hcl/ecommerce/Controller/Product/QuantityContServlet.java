package com.hcl.ecommerce.Controller.Product;

import com.hcl.ecommerce.Model.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "QuantityContServlet", value = "/quantity-control")
public class QuantityContServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try{
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));

            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            if (action != null && id >= 1){
                if(action.equals("inc")){
                    for (Cart c:cart_list){
                        if(c.getId() == id){
                            int quantity = c.getQuantity();
                            quantity++;
                            c.setQuantity(quantity);
                            response.sendRedirect("ShoppingCart.jsp");
                        }
                    }
                }
                if(action.equals("dec")){
                    for (Cart c:cart_list){
                        if(c.getId() == id){
                            int quantity = c.getQuantity();
                            quantity--;
                            c.setQuantity(quantity);
                            response.sendRedirect("ShoppingCart.jsp");
                        }
                    }
                }
            }
        } catch (NumberFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
