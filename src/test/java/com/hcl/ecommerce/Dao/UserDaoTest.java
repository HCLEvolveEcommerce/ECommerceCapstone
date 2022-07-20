package com.hcl.ecommerce.Dao;

import com.hcl.ecommerce.Model.User;
import jdk.jfr.Name;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.hcl.ecommerce.Model.DbCon;
import org.junit.runner.JUnitCore;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest extends TestCase {
    UserDao userDao;

    UserDaoTest() throws SQLException, ClassNotFoundException {
        userDao = new UserDao(DbCon.getConnection());
    }

    @Before
    public void create() throws SQLException, ClassNotFoundException {
        userDao = new UserDao(DbCon.getConnection());
    }

    @BeforeEach
    public void setUp() {

        System.out.println("UserDao Tests:");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("UserDao Test complete. ");
    }

    @Test
    void checkUserDao() {
        Assertions.assertNotNull(userDao, "UserDao Object is null, not connected to DB");
    }
    //Test for valid user login.
    @Test
    @Name("Valid User Login")
    void userLogin() {
        User user = userDao.userLogin("dsikes313@gmail.com", "BeastMode");
        Assertions.assertNotNull(user, "This user should not be null");
        //Check valid user's id
        Assertions.assertEquals(2, user.getId());

    }
    //Get all users test
    @Test
    void getAllUsers() {
    }

    @Test
    void selectUser() {
    }

    @Test
    void insertUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}