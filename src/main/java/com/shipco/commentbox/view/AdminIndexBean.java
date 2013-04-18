/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.view;

import com.shipco.commentbox.exception.WrongValueException;
import com.shipco.commentbox.model.Email;
import com.shipco.commentbox.repository.EmailRepository;
import com.shipco.commentbox.service.EmailService;
import com.shipco.commentbox.utils.SpringUtils;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author San
 */
@ManagedBean
@SessionScoped
public class AdminIndexBean implements Serializable {

    private String newEmail;
    private List<Email> emails;
    private EmailService emailService;

    @PostConstruct
    public void init() {
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

    public void addEmail() {
        Email email = new Email();
        email.setEmail(getNewEmail());
        try {
            email = emailService.addEmail(email);
            emails.add(email);
        } catch (WrongValueException ex) {
            popupMessage("Invalid Email", ex.getMessage());
        }

        newEmail = new String();
    }

    public void removeEmail(Email email) {
        emailService.removeEmail(email);
        emails.remove(email);
    }

    private void popupMessage(String summary, String detail) {
        FacesMessage msg = new FacesMessage(summary, detail);
        FacesContext.getCurrentInstance().addMessage("messages", msg);
    }
}
