/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.view;

import com.shipco.commentbox.authenticate.DuplicateUsernameException;
import com.shipco.commentbox.model.User;
import com.shipco.commentbox.service.UserService;
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
 * @author wjirawong
 */
@ManagedBean
@SessionScoped
public class UserManageBean implements Serializable {

    private UserService userService;
    private List<User> users;
    private String username;
    private String email;

    @PostConstruct
    public void init() {
        userService = SpringUtils.getBean(UserService.class);
        users = userService.getAllUser();

    }

    public void addNewUser() {
        try {
            User user = userService.addNewUser(username, email);
            userService.sendEmailForNewUser(user);
            users = userService.getAllUser();
        } catch (DuplicateUsernameException ex) {
            popupMessage("Add Failed", ex.getMessage());
        }
    }

    private void popupMessage(String summary, String detail) {
        FacesMessage msg = new FacesMessage(summary, detail);
        FacesContext.getCurrentInstance().addMessage("messages", msg);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
