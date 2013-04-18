/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.service.impl;

import com.shipco.commentbox.model.User;
import com.shipco.commentbox.repository.UserRepository;
import com.shipco.commentbox.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User addNewUser(String username, String email) {
        User user = findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(generatePassword());
            user.setEmail(email);
            return userRepository.addUser(user);
        }else{
            return null;
        }
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
