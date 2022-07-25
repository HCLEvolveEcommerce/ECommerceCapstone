package com.hcl.ecommerce.Dao;

import com.hcl.ecommerce.Model.Cart;
import com.hcl.ecommerce.Model.Order;
import com.hcl.ecommerce.Model.Product;
import com.hcl.ecommerce.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hcl.ecommerce.Model.DbCon.getConnection;

public class ProductDao {

    private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products" + " (name, category, price, image) VALUES " + " (?, ?, ?, ?);";
    private static final String SELECT_PRODUCT_BY_ID = "select * from products where id=?";
    private static final String SELECT_ALL_PRODUCTS = "select * from products";
    private static final String QUERY_CHECK = "select * from products WHERE name = ?";
    private static final String DELETE_PRODUCT_SQL = "delete from products where id=?";
    private static final String SELECT_PRICE_BY_ID = "select price from products where id=?";
    private static final String UPDATE_PRODUCT_SQL = "update products set name = ?, category=?, price=? , image=? where id=?";
    private static final String TYPE_CHECK = "select*from products WHERE productav =?";
    private static final String SELECT_PRODUCT_BY_NAME = "select * from products where name = ?";

    private Connection con;

    private String query;
    private PreparedStatement pst;
    private ResultSet rs;


    public ProductDao(Connection con) {
        super();
        this.con = con;
    }


    public List<Product> getAllProducts() {
        List<Product> book = new ArrayList<>();
        try {

            query = SELECT_ALL_PRODUCTS;
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Product row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));

                book.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }

    public List<Cart> ProductCart(ArrayList<Cart> cartList) {
        List<Cart> book = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = SELECT_PRODUCT_BY_ID;
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getDouble("price")*item.getQuantity());
                        row.setImage(rs.getString("image"));
                        row.setQuantity(item.getQuantity());
                        book.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }

    public boolean deleteProducts(int id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);){
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }


    public Product getSingleProduct(int id) {
        Product row = null;
        try {
            query = SELECT_PRODUCT_BY_ID;
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return row;
    }
    public Product getProductByName(String name) {
        Product row = null;
        try {
            query = SELECT_PRODUCT_BY_NAME;
            pst = this.con.prepareStatement(query);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return row;
    }
    public void insertProduct(Product product) throws SQLException, ClassNotFoundException { //untested
                try {
                    query = INSERT_PRODUCTS_SQL;
                    pst = con.prepareStatement(query);
                    pst.setString(1, product.getName());
                    System.out.println("did I get here");
                    pst.setString(2, product.getCategory());
                    System.out.println("category");
                    pst.setDouble(3, product.getPrice());
                    pst.setString(4, product.getImage());
                    System.out.println(pst);
                    pst.executeUpdate();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
    }

    public double subTotalPrice(ArrayList<Cart> cartList){
        double sum = 0;

        try{
            if(cartList.size() > 0){
                for(Cart item:cartList) {
                    pst = con.prepareStatement(SELECT_PRICE_BY_ID);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();


                    while (rs.next()) {
                        sum += rs.getDouble("price") * item.getQuantity();
                        String.format("%.20f", sum);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }

    public double totalPrice(ArrayList<Cart> cartList){
        double sum = 0;
        int shipping = 10;


        try{
            if(cartList.size() > 0){
                for(Cart item:cartList) {
                    pst = con.prepareStatement(SELECT_PRICE_BY_ID);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();


                    while (rs.next()) {
                        sum += rs.getDouble("price") * item.getQuantity();
                        String.format("%.20f", sum);
                    }
                }
                double tax = sum * .0625;
                sum += tax;
                sum += shipping;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }

    public double totalTax(ArrayList<Cart> cartList){
        double sum = 0;
        double tax = 0;
        int shipping = 10;


        try{
            if(cartList.size() > 0){
                for(Cart item:cartList) {
                    pst = con.prepareStatement(SELECT_PRICE_BY_ID);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();


                    while (rs.next()) {
                        sum += rs.getDouble("price") * item.getQuantity();
                        String.format("%.20f", sum);
                    }
                }
                tax = sum * .0625;
                sum += tax;
                sum += shipping;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tax;
    }


    public boolean updateProduct(Product product) throws SQLException{
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);){
            System.out.println("Updated Product: " + statement);
            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getImage());
            statement.setInt(5, product.getId());


            rowUpdated = statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }
}

