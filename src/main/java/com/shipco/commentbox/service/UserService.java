/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.service;

import com.shipco.commentbox.authenticate.DuplicateUsernameException;
import com.shipco.commentbox.model.User;
import java.util.List;

/**
 *
 * @author wjirawong
 */
public interface UserService {
    public User findByUsername(String username);
    public List<User> getAllUser();
    public User addNewUser(String username,String email) throws DuplicateUsernameException;
    public void sendEmailForNewUser(User user);
}
