/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.repository.impl;

import com.shipco.commentbox.model.Email;
import com.shipco.commentbox.repository.EmailRepository;
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
public class EmailRepositoryImpl implements EmailRepository{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Email> getAllEmail(){
        return em.createQuery("SELECT e FROM Email e").getResultList();
    }
    
    @Override
    public void removeEmail(Email email){
        Email merge = em.merge(email);
        em.remove(merge);
    }
    
    @Override
    public Email addEmail(Email email){
        em.persist(email);
        em.flush();
        return email;
    }
}
