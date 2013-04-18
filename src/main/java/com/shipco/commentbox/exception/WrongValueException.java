/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.exception;

/**
 *
 * @author San
 */
public class WrongValueException extends Exception{
    public WrongValueException(){
        super();
    }
    
    public WrongValueException(String message){
        super(message);
    }
}
