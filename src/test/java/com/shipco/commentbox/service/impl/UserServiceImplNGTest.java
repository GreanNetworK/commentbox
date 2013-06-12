/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.service.impl;

import com.shipco.commentbox.exception.DuplicateUsernameException;
import com.shipco.commentbox.exception.WrongValueException;
import com.shipco.commentbox.model.User;
import com.shipco.commentbox.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author San
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceImplNGTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private UserService userService;
    
    private final String USERNAME = "test";
    private final String EMAIL = "test@test.com";

    public UserServiceImplNGTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
       userService.addNewUser(USERNAME, EMAIL);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of findByUsername method, of class UserServiceImpl.
     */
    @Test
    public void testFindByUsername() {
        System.out.println("findByUsername");
        User result = userService.findByUsername(USERNAME);
        assertNotNull(result);
        assertEquals(result.getUsername(), USERNAME);
        assertEquals(result.getPassword().length(), 8);
        assertEquals(result.getEmail(), EMAIL);
    }

    /**
     * Test of getAllUser method, of class UserServiceImpl.
     */
    @Test
    public void testGetAllUser() {
        System.out.println("getAllUser");
        List<User> results = userService.getAllUser();
        assertNotNull(results);
        assertNotSame(results.size(), 0);
    }

    /**
     * Test of addNewUser method, of class UserServiceImpl.
     */
    @Test
    public void testAddNewUser() throws DuplicateUsernameException, WrongValueException {
        System.out.println("addNewUser");
        String username = "testAdd";
        String email = "testAdd";
        User user = userService.addNewUser(username, email);
        assertNotNull(user);
        assertEquals(user.getUsername(), username);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getPassword().length(), 8);
    }
}