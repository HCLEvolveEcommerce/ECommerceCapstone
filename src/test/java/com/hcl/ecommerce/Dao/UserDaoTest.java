package com.hcl.ecommerce.Dao;

import com.hcl.ecommerce.Model.User;
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
        User user = userDao.selectUser(2);
        assertEquals("Julian ", user.getFirstname());
    }

    @Test
    void insertUser() {

    }

    @Test
    void TestUInsertAndDelete() throws SQLException, ClassNotFoundException {

        userDao.insertUser(new User("John","johnsmith@yahoo.com", "johnsmith", "Client", "1824 Auburn Dr.", "Carrollton", 75007, "tx", "usa","Smith", "42932123"));
        //need to update to newest id
        User dummyUser = userDao.userLogin("johnsmith@yahoo.com","johnsmith");
        assertEquals("John", dummyUser.getFirstname());
        assert(userDao.deleteUser(dummyUser.getId()));
        assertFalse(userDao.deleteUser(12321));
    }

    @Test
    void updateUser() {
    }

}