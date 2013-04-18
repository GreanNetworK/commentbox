/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.service.impl;

import com.shipco.commentbox.model.Email;
import com.shipco.commentbox.repository.EmailRepository;
import com.shipco.commentbox.service.EmailService;
import com.shipco.commentbox.view.IndexBean;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author wjirawong
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public List<Email> getAllEmail() {
        return emailRepository.getAllEmail();
    }

    @Override
    public List<InternetAddress> getAllEmailInternetAddress() {
        List<Email> allEmail = getAllEmail();
        List<InternetAddress> recipients = new ArrayList<InternetAddress>();
        try {
            for (Email email : allEmail) {
                recipients.add(new InternetAddress(email.getEmail()));
            }
        } catch (AddressException ex) {
            Logger.getLogger(IndexBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recipients;
    }

    @Override
    public void removeEmail(Email email) {
        emailRepository.removeEmail(email);
    }

    @Override
    public void addEmail(Email email) {
        emailRepository.addEmail(email);
    }
}
