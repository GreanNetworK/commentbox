/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.repository.impl;

import com.shipco.commentbox.model.User;
import com.shipco.commentbox.repository.UserRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wjirawong
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class UserRepositoryImpl implements UserRepository{
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public User findByUsername(String username){
        User singleResult = (User) em.find(User.class, username);
        return singleResult;
    }

    @Override
    public List<User> getAllUser() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public User addUser(User user) {
        em.persist(user);
        em.flush();
        return user;
    }
}
