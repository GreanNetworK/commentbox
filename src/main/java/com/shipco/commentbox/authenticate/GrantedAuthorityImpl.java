/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.authenticate;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author San
 */
public class GrantedAuthorityImpl implements GrantedAuthority{
    
    String authority;
    
    public GrantedAuthorityImpl(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
    
}
