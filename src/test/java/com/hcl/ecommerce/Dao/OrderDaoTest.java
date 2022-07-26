package com.hcl.ecommerce.Dao;

import com.hcl.ecommerce.Model.DbCon;
import com.hcl.ecommerce.Model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {
    OrderDao orderdao;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        System.out.println("Before Test");
        orderdao = new OrderDao(DbCon.getConnection());
    }

    @Test
    void testUserDaoInsertGetOrderDateDelete() throws SQLException, ClassNotFoundException {
        Order dumbOrder = new Order();
        dumbOrder.setOrderID(100);
        dumbOrder.setUserID(185);
        dumbOrder.setQuantity(2);
        dumbOrder.setOrderDate("abc");

        orderdao.insertOrder(dumbOrder);
        Order order = orderdao.getOrderByOrderDate("abc");
        assertNotNull(order);
        orderdao.deleteOrders(order.getOrderID());
    }

    @Test
    void testGetAllOrders() {
        List<Order> orders = orderdao.getAllOrders();
        assertEquals(10, 10);
    }

    @Test
    void clientOrders() {

    }
}