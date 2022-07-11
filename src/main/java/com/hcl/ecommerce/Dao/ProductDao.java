package com.hcl.ecommerce.Dao;

import com.hcl.ecommerce.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products" + " (productname, productav, quantity) VALUES " + " (?, ?);";
    private static final String SELECT_PRODUCT_BY_ID = "select * from products where productid=?";
    private static final String SELECT_ALL_PRODUCTS = "select * from products";
    private static final String DELETE_PRODUCT_SQL = "delete from products where productid=?";
    private static final String UPDATE_PRODUCT_SQL = "update products set productname = ?, productav=? where productid=?";
    private static final String TYPE_CHECK = "select*from products WHERE productav =?";


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

    public void insertProduct(Product product) throws SQLException{ //untested
        try {
            query = INSERT_PRODUCTS_SQL;
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            pst.setInt(1, product.getId());
            try {
                pst.setString(1, product.getName());
                pst.setString(2, product.getCategory());
                pst.setDouble(3, product.getPrice());
                pst.setString(4, product.getImage());
                pst.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

