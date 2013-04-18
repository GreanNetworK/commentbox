/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.repository;

import com.shipco.commentbox.model.Email;
import java.util.List;

/**
 *
 * @author wjirawong
 */
public interface EmailRepository {
    public List<Email> getAllEmail();
    public void removeEmail(Email email);
    public void addEmail(Email email);
}
