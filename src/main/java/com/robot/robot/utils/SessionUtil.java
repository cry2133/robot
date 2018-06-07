package com.robot.robot.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class SessionUtil {
	@SuppressWarnings("unchecked")
	public static <T> T getAttr( String key){
		Subject subject = SecurityUtils.getSubject();  
		Session session = subject.getSession(); 
		return (T)session.getAttribute(key);
	}
	
	/**
     * 设置request的Attribute
     * @param name
     * @param value
     */
    public static void setRequestAttribute(String name,Object value){  
    	Subject subject = SecurityUtils.getSubject();  
		Session session = subject.getSession(); 
		session.setAttribute(name, value);
    } 
    
    /**
     * 删除session中的Attribute
     * @param name
     */
      public static void removeSessionAttribute(String name) { 
    	Subject subject = SecurityUtils.getSubject();  
  		Session session = subject.getSession(); 
  		session.removeAttribute(name);  
      }  
}
