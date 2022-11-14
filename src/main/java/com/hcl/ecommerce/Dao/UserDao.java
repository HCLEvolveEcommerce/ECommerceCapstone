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

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + " (firstname, email, password, usertype, address, city, zip, state, country, lastname, phonenumber) VALUES " + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select id, firstname,email,usertype, password, address, city, zip, state, country, lastname, phonenumber from users where id=?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id=?";
    private static final String UPDATE_USERS_SQL = "update users set firstname = ?, email=?, password=? , usertype=?, address=?, city=?, zip=?, state=?, country=?, lastname=?, phonenumber=? where id=?";
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
                user.setFirstname(rs.getString("firstname"));
                user.setEmail(rs.getString("email"));
                user.setUsertype(rs.getString("usertype"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setZip(rs.getInt("zip"));
                user.setState(rs.getString("state"));
                user.setCountry(rs.getString("country"));
                user.setLastname(rs.getString("lastname"));
                user.setPhonenumber(rs.getString("phonenumber"));
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
                row.setFirstname(rs.getString("firstname"));
                row.setEmail(rs.getString("email"));
                row.setPassword(rs.getString("password"));
                row.setUsertype(rs.getString("usertype"));
                row.setAddress(rs.getString("address"));
                row.setCity(rs.getString("city"));
                row.setZip(rs.getInt("zip"));
                row.setState(rs.getString("state"));
                row.setCountry(rs.getString("country"));
                row.setLastname(rs.getString("lastname"));
                row.setPhonenumber(rs.getString("phonenumber"));

                book.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }

    public User selectUser(int id){
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String usertype = rs.getString("usertype");
                String address = rs.getString("address");
                String city = rs.getString("city");
                int zip = rs.getInt("zip");
                String state = rs.getString("state");
                String country = rs.getString("country");
                String lastname = rs.getString("lastname");
                String phonenumber = rs.getString("phonenumber");
                user = new User(id, firstname, email, password, usertype, address, city, zip, state, country, lastname, phonenumber);
            }
        } catch (SQLException | ClassNotFoundException e){
            assert e instanceof SQLException;
            printSQLException((SQLException) e);
        }
        return user;
    }

    public void insertUser(User user) throws SQLException, ClassNotFoundException { //untested
        System.out.println(INSERT_USERS_SQL);
        try(Connection connection = getConnection(); // Implemented Duplicate Value Check
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CHECK)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, user.getEmail());
            System.out.println(preparedStatement);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final int count = resultSet.getInt(1);
                preparedStatement.executeUpdate();
                System.out.println(preparedStatement);
            }
        }

        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUsertype());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getCity());
            preparedStatement.setInt(7, user.getZip());
            preparedStatement.setString(8, user.getState());
            preparedStatement.setString(9, user.getCountry());
            preparedStatement.setString(10, user.getLastname());
            preparedStatement.setString(11, user.getPhonenumber());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUser(int id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);){
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException{
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);){
            System.out.println("updated User: " + statement);
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUsertype());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getCity());
            statement.setInt(7, user.getZip());
            statement.setString(8, user.getState());
            statement.setString(9, user.getCountry());
            statement.setString(10, user.getLastname());
            statement.setString(11, user.getPhonenumber());
            statement.setInt(12, user.getId());


            rowUpdated = statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
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
