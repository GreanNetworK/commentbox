/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.service;

import com.shipco.commentbox.model.Email;
import java.util.List;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author wjirawong
 */
public interface EmailService {
    public List<Email> getAllEmail();
    public List<InternetAddress> getAllEmailInternetAddress();
    public void removeEmail(Email email);
    public void addEmail(Email email);
}
