/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.web.converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class MappingFastjsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {
	 
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
     
    private SerializerFeature[] features;  
   
 
    
    public SerializerFeature[] getFeatures() {
		return features;
	}

	public void setFeatures(SerializerFeature[] features) {
		this.features = features;
	}

	public MappingFastjsonHttpMessageConverter() {  
        super(new MediaType("application", "json", DEFAULT_CHARSET));  
    }  
   
    @Override 
    public boolean canRead(Class<?> clazz, MediaType mediaType) {  
        return true;  
    }  
   
    @Override 
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {  
        return true;  
    }  
   
    @Override 
    protected boolean supports(Class<?> clazz) {  
        throw new UnsupportedOperationException();
    }
     
    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
    	 ByteArrayOutputStream baos = new ByteArrayOutputStream();   
         int i;   
         while ((i = inputMessage.getBody().read()) != -1) {   
             baos.write(i);
         }   
         return JSON.parseArray(baos.toString(), clazz);
    }
     
    @Override 
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {  
    	 //JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
    	String jsonString = JSON.toJSONString(o, features); 
        OutputStream out = outputMessage.getBody();  
        out.write(jsonString.getBytes(DEFAULT_CHARSET));  
        out.flush();
    }  
}