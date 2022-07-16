package com.hcl.ecommerce.Controller.User;

import com.hcl.ecommerce.Dao.OrderDao;
import com.hcl.ecommerce.Dao.UserDao;
import com.hcl.ecommerce.Model.DbCon;
import com.hcl.ecommerce.Model.Order;
import com.hcl.ecommerce.Model.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@WebServlet(name = "UserOrderServlet", value = "/user-order")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try{
            User auth = (User) session.getAttribute("auth"); //play with this //maye have to revert back to request.getSession.get
            if (auth != null) {
                String prodId= request.getParameter("id");
                int prodQuant= Integer.parseInt(request.getParameter("quantity"));

                if (prodQuant <=0 ){
                    prodQuant = 1; //check this logic
                }

                Order order = new Order();
                order.setId(Integer.parseInt(prodId));
                order.setUserID(auth.getId());
                order.setQuantity(prodQuant);
                order.setOrderDate(formatter.format(date));

                OrderDao orderDao = new OrderDao(DbCon.getConnection());
                orderDao.insertOrder(order);
                response.sendRedirect("AccountPage.jsp");



            }else{
                String prodId= request.getParameter("id");
                int prodQuant= Integer.parseInt(request.getParameter("quantity"));
                if (prodQuant <=0 ){
                    prodQuant = 1; //check this logic
                }
                    Order order = new Order();
                    order.setId(Integer.parseInt(prodId));
                    order.setUserID(0);
                    order.setQuantity(prodQuant);
                    order.setOrderDate(formatter.format(date));

                    OrderDao orderDao = new OrderDao(DbCon.getConnection());
                    orderDao.insertOrder(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
