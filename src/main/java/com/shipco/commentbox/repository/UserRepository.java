/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.repository;

import com.shipco.commentbox.model.User;
import java.util.List;

/**
 *
 * @author wjirawong
 */
public interface UserRepository {
    public User findByUsername(String username);
    public List<User> getAllUser();
    public User addUser(User user);
}
