/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.*;

/**
 *
 * @author wjirawong
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringUtilsNGTest extends AbstractTestNGSpringContextTests{
    
    public SpringUtilsNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getBean method, of class SpringUtils.
     */
    //@Test
    public void testGetBean_Class() {
        System.out.println("getBean");
        Class<?> type = null;
        Object expResult = null;
        Object result = SpringUtils.getBean(type);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBean method, of class SpringUtils.
     */
    //@Test
    public void testGetBean_String_Class() {
        System.out.println("getBean");
        String name = "";
        Class<?> type = null;
        Object expResult = null;
        Object result = SpringUtils.getBean(name, type);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBean method, of class SpringUtils.
     */
    //@Test
    public void testGetBean_String() {
        System.out.println("getBean");
        String name = "";
        Object expResult = null;
        Object result = SpringUtils.getBean(name);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setApplicationContext method, of class SpringUtils.
     */
    @Test
    public void testGetApplicationContext() {
        System.out.println("setApplicationContext");
        ApplicationContext ac = SpringUtils.getApplicationContext();
        assertNotNull(ac);
    }
}