package com.hcl.ecommerce.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {

    private static Connection connection = null;
//    private static String jdbcURL = "jdbc:mysql://userdbazure.mysql.database.azure.com:3306/userdb";
private static String jdbcURL ="jdbc:postgresql:userdb";
    private static String jdbcUserName = "postgres";
    private static String jdbcPassword = "Fbies#13188";
    private static String jdbcDriver = "org.postgresql.Driver";


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try{ //this vs if allows us to keep trying the connection and not close it if connection == null
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
            System.out.println("Im connected");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

// ECF-7 testing