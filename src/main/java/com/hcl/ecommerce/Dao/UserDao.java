package com.hcl.ecommerce.Dao;

import com.hcl.ecommerce.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hcl.ecommerce.Model.DbCon.getConnection;

public class UserDao {
    private Connection connection;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + " (name, email, password) VALUES " + " (?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select id, name,email,usertype, password from users where id=?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id=?";
    private static final String UPDATE_USERS_SQL = "update users set name = ?, email=?, usertype=?, password=? where id=?";
    private static final String QUERY_CHECK = "select * from users WHERE email = ?";
    private static final String LOGIN_CHECK = "select * from users WHERE email = ? AND password =? ";
    private static final String TYPE_CHECK = "select*from users WHERE usertype =?";


    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User userLogin(String email, String password) {
        User user = null;
        try {
            query = LOGIN_CHECK;
            pst = connection.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> book = new ArrayList<>();
        try {

            query = SELECT_ALL_USERS;
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                User row = new User();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setEmail(rs.getString("email"));
                row.setPassword(rs.getString("password"));

                book.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }

    public void insertUser(User user) throws SQLException, ClassNotFoundException { //untested
        System.out.println(INSERT_USERS_SQL);
        try(Connection connection = getConnection(); // Implemented Duplicate Value Check
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CHECK)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, user.getEmail());
            System.out.println(preparedStatement);
            final ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("ive gotten to execute");
            if (resultSet.next()) {
                System.out.println("im in the if statement");
                final int count = resultSet.getInt(1);
                preparedStatement.executeUpdate();
                System.out.println(preparedStatement);
            }
        }
        System.out.println("im passed if ");

        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            System.out.println("im within try #2 ");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            System.out.println("before the catch");

        } catch (SQLException ignored) {
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                ex.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
