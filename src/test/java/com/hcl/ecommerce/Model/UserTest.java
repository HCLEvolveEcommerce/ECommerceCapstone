package com.hcl.ecommerce.Model;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest extends TestCase {

    @BeforeEach
    public void SetUp() {
        System.out.println("Test is being set up.");
    }

    @Test
    public void testFirstNameMethod() {
        User user = new User();
        user.setFirstname("Julian");
        assertEquals(user.getFirstname(), "Julian");
        System.out.println(user.getFirstname() + " = " + "Julian");
    }



}