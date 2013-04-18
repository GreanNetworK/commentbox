/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.view;

import com.shipco.commentbox.model.Email;
import com.shipco.commentbox.repository.EmailRepository;
import com.shipco.commentbox.service.EmailService;
import com.shipco.commentbox.utils.SpringUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author San
 */
@ManagedBean
@SessionScoped
public class AdminIndexBean implements Serializable{

    private String newEmail;
    private List<Email> emails;
    private EmailService emailService;
    
    @PostConstruct
    public void init(){
        emailService = SpringUtils.getBean(EmailService.class);
        emails = emailService.getAllEmail();
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
    
    public void addEmail(){
        Email email = new Email();
        email.setEmail(getNewEmail());
        emailService.addEmail(email);
        emails.add(email);
        
        newEmail = new String();
    }
    
    public void removeEmail(Email email){
        emailService.removeEmail(email);
        emails.remove(email);
    }
}
