/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.service.impl;

import com.shipco.commentbox.authenticate.DuplicateUsernameException;
import com.shipco.commentbox.model.User;
import com.shipco.commentbox.repository.UserRepository;
import com.shipco.commentbox.service.UserService;
import com.shipco.commentbox.utils.EmailUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 *
 * @author wjirawong
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.getAllUser();
    }

    @Override
    public User addNewUser(String username, String email) throws DuplicateUsernameException {
        User user = findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(generatePassword());
            user.setEmail(email);
            User addUser = userRepository.addUser(user);
            return addUser;
        } else {
            throw new DuplicateUsernameException();
        }
    }

    @Override
    public void sendEmailForNewUser(User user) {
        StringBuilder builder = new StringBuilder();
        builder.append("Hi,<br>");
        builder.append("you have invite for <b>Comment Box Control Panel</b><br><br>");
        builder.append("<u><b>your account detail</b></u><br>");
        builder.append("URL : <a href='http://172.16.73.50:8080/commentbox/login.xhtml'>http://172.16.73.50:8080/commentbox/login.xhtml</a><br>");
        builder.append("Username : <b>");
        builder.append(user.getUsername());
        builder.append("</b><br>");
        builder.append("Password : <b>");
        builder.append(user.getPassword());
        builder.append("</b><br>");
        EmailUtils.sendEmailMessage("Welcome to Comment Box!!!", builder.toString(), user.getEmail());
    }

    private String generatePassword() {
        int len = 8;
        char[] pwd = new char[len];
        int c = 'A';
        int rand;
        for (int i = 0; i < 8; i++) {
            rand = (int) (Math.random() * 3);
            switch (rand) {
                case 0:
                    c = '0' + (int) (Math.random() * 10);
                    break;
                case 1:
                    c = 'a' + (int) (Math.random() * 26);
                    break;
                case 2:
                    c = 'A' + (int) (Math.random() * 26);
                    break;
            }
            pwd[i] = (char) c;
        }
        return new String(pwd);
    }
}
