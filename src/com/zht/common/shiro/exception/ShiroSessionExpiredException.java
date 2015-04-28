package com.zht.common.shiro.exception;

public class ShiroSessionExpiredException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShiroSessionExpiredException() { 
	      super(); 
	   } 
	   
	   public ShiroSessionExpiredException(String message, Throwable cause) { 
	      super(message, cause); 
	   } 
	   
	   public ShiroSessionExpiredException(String message) { 
	      super(message); 
	   } 
	   
	   public ShiroSessionExpiredException(Throwable cause) { 
	      super(cause); 
	   } 
}
