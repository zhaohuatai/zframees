/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.shiro.jcaptcha;

import org.apache.shiro.authc.AuthenticationException;

public class IncorrectCaptchaException extends AuthenticationException{ 
	   
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncorrectCaptchaException() { 
	      super(); 
	   } 
	   
	   public IncorrectCaptchaException(String message, Throwable cause) { 
	      super(message, cause); 
	   } 
	   
	   public IncorrectCaptchaException(String message) { 
	      super(message); 
	   } 
	   
	   public IncorrectCaptchaException(Throwable cause) { 
	      super(cause); 
	   } 
}