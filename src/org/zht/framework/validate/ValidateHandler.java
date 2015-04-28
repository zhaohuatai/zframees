/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.validate;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.NativeWebRequest;
public class ValidateHandler {
	
        public static ValidateResult handle(BindingResult result){
        	ValidateResult retVal = new ValidateResult();
            if(result.hasErrors()){
                List<ObjectError> list = result.getAllErrors();
                ObjectError oe = list.get(0);
                retVal.setMessage(oe.getDefaultMessage());
                retVal.setResult(false);
            }else{
                retVal.setResult(true);
            }
            return retVal;
        }
        
        public static String getDefaultError(BindingResult result){
            if(result.hasErrors()){
            	List<FieldError> fieldEist =result.getFieldErrors();
            	if(fieldEist!=null&&fieldEist.size()>0){
            		FieldError fieldError=fieldEist.get(0);
            		fieldError.getCode();
            		String prefix="\r\n"+"所提交数据：";
            		String value="{"+(fieldError.getRejectedValue()==null?" ":fieldError.getRejectedValue())+"}";
            		String message=""+fieldError.getDefaultMessage();
            		return prefix+""+value+"\r\n"+message;
            	}else{
            		 List<ObjectError> allErrorlist = result.getAllErrors();
                     ObjectError oe = allErrorlist.get(0);
                     return ""+oe.getDefaultMessage();
            	}
            }else{
            	return null;
            }
        }
        
        @SuppressWarnings("unchecked")
        public static String getDefaultErrorFromResolver(NativeWebRequest request){
			List<BindingResult> list = (List<BindingResult>)request.getAttribute(ValidateConstant.BINDING_RESULT_LIST_NAME, 0);
    		if(list!=null&&list.size()>0){
    			String error=getDefaultError(list.get(0));
    			return error==null?"":error;
    		}
            return null;
        }

		public static boolean hasErrors(NativeWebRequest request) {
			Boolean hasError=(Boolean) request.getAttribute(ValidateConstant.BINDING_RESULT_HAS_ERROR, 0);
    		if(hasError!=null&&hasError){
    			return true;
    		}
			return false;
		}
}
