/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.authenticate;

import com.shipco.commentbox.model.User;
import com.shipco.commentbox.service.UserService;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author wjirawong
 */
public class UserDetailServiceImpl implements UserDetailsService{
    
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try{
            user = userService.findByUsername(username);
        }catch(EntityNotFoundException ex){
            throw new UsernameNotFoundException("Please Check Username and Password.");
        }
        return user;
    }
    
}
