package com.hcl.ecommerce.Dao;

import com.hcl.ecommerce.Model.Order;
import com.hcl.ecommerce.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private static final String INSERT_ORDERS_SQL = "INSERT INTO orders" + " (product_id, user_id, order_quantity, order_date) VALUES " + " (?, ?, ?, ?);";
    private static final String SELECT_ORDER_BY_ID = "select * from orders where id=?";
    private static final String SELECT_ALL_ORDERS = "select * from orders";
    private static final String QUERY_CHECK = "select * from orders WHERE name = ?";
    private static final String DELETE_ORDERS_SQL = "delete from orders where id=?";
    private static final String SELECT_ORDERS_BY_ID = "select price from orders where id=?";
    private static final String UPDATE_ORDER_SQL = "update orders set name = ?, category=?, price=? , image=? where id=?";


    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public OrderDao(Connection connection) {
        super();
        this.connection = connection;
    }

    public boolean insertOrder(Order order) throws SQLException {
        boolean result = false;

        try {
            query = INSERT_ORDERS_SQL;
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, order.getUserID());
            preparedStatement.setInt(3, order.getQuantity());
            preparedStatement.setString(4, order.getOrderDate()); //may have to edit
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    public List<Order> getAllOrders() {
        List<Order> book = new ArrayList<>();
        try {

            query = SELECT_ALL_ORDERS;
            preparedStatement = this.connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order row = new Order();
                row.setOrderID(resultSet.getInt("order_id"));
                row.setId(resultSet.getInt("product_id"));
                row.setUserID(resultSet.getInt("user_id"));
                row.setOrderDate(resultSet.getString("order_date"));
                row.setQuantity(resultSet.getInt("order_quantity"));

                book.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }
}