package com.hcl.ecommerce.Controller.User;

import com.hcl.ecommerce.Dao.OrderDao;
import com.hcl.ecommerce.Model.Cart;
import com.hcl.ecommerce.Model.DbCon;
import com.hcl.ecommerce.Model.Order;
import com.hcl.ecommerce.Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "OrderAllServlet", value = "/order-all")
public class OrderAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            ArrayList<Cart> cart_list = (ArrayList<Cart>)  request.getSession().getAttribute("cart-list");
            User auth = (User) session.getAttribute("auth");

            if (cart_list != null) {
                if (auth != null){
                    for (Cart c: cart_list){
                        Order order = new Order();
                        order.setId(c.getId());
                        order.setUserID(auth.getId());
                        order.setQuantity(c.getQuantity());
                        order.setOrderDate(formatter.format(date));

                        System.out.println("before dao");
                        OrderDao orderDao = new OrderDao(DbCon.getConnection());
                        orderDao.insertOrder(order);
                        System.out.println("end of for");

                    }
                    response.sendRedirect("orders.jsp");
                    cart_list.clear();
                }
                else {
                    for (Cart c: cart_list){
                        Order order = new Order();
                        order.setId(c.getId());
                        order.setUserID(0);
                        order.setQuantity(c.getQuantity());
                        order.setOrderDate(formatter.format(date));
                        System.out.println("before dao");
                        OrderDao orderDao = new OrderDao(DbCon.getConnection());
                        orderDao.insertOrder(order);
                        System.out.println("end of for");
                    }
                    response.sendRedirect("orders.jsp");
                    cart_list.clear();

                }
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/order-email");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
