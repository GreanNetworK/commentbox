/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author wjirawong
 */
@Component("springUtils")
public class SpringUtils implements ApplicationContextAware{
    
    private static ApplicationContext context;
    
    public static <T extends Object> T getBean(Class<T> type){
        return context.getBean(type);
    }
    
    public static <T extends Object> T getBean(String name,Class<T> type){
        return context.getBean(name, type);
    }
    
    public static Object getBean(String name){
        return context.getBean(name);
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        context = ac;
    }
    
}
