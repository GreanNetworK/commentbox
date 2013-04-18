/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.service.impl;

import com.shipco.commentbox.model.Email;
import com.shipco.commentbox.service.EmailService;
import java.util.List;
import javax.mail.internet.InternetAddress;
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
public class EmailServiceImplNGTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    private EmailService emailService;
    
    private Email email;
    
    public EmailServiceImplNGTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        email = new Email();
        email.setEmail("test@test.com");
        email = emailService.addEmail(email);
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
     * Test of getAllEmail method, of class EmailServiceImpl.
     */
    @Test
    public void testGetAllEmail() {
        System.out.println("getAllEmail");
        List<Email> emails = emailService.getAllEmail();
        assertNotNull(emails);
        assertNotSame(emails.size(), 0);
    }

    /**
     * Test of getAllEmailInternetAddress method, of class EmailServiceImpl.
     */
    @Test
    public void testGetAllEmailInternetAddress() {
        System.out.println("getAllEmailInternetAddress");
        List<InternetAddress> emails = emailService.getAllEmailInternetAddress();
        assertNotNull(emails);
        assertNotSame(emails.size(), 0);
    }

    /**
     * Test of removeEmail method, of class EmailServiceImpl.
     */
    @Test
    public void testRemoveEmail() {
        System.out.println("removeEmail");
        List<Email> beforeEmails = emailService.getAllEmail();
        emailService.removeEmail(email);
        List<Email> afterEmails = emailService.getAllEmail();
        assertNotSame(beforeEmails.size(), afterEmails.size());
    }

    /**
     * Test of addEmail method, of class EmailServiceImpl.
     */
    @Test
    public void testAddEmail() {
        System.out.println("addEmail");
        Email testEmail = new Email();
        String strEmail = "xxx@xxx.com";
        testEmail.setEmail(strEmail);
        Email addEmail = emailService.addEmail(testEmail);
        assertNotNull(addEmail);
        assertNotSame(addEmail.getId(), 0);
        assertEquals(addEmail.getEmail(), strEmail);
    }
}