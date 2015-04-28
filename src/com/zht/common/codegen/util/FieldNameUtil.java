
/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldNameUtil {
	/**
	 * 获取对象属性，返回一个字符串数组
	 * 
	 * @param o
	 *            对象
	 * @return String[] 字符串数组
	 */
	public static String[] getFiledName(Object o) {
		try {
			Field[] fields = o.getClass().getDeclaredFields();
			String[] fieldNames = new String[fields.length];
			for (int i = 0; i < fields.length; i++) {
				fieldNames[i] = fields[i].getName();
			}
			return fieldNames;
		} catch (SecurityException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return null;
	}

	/**
	 * 使用反射根据属性名称获取属性值
	 * 
	 * @param fieldName
	 *            属性名称
	 * @param o
	 *            操作对象
	 * @return Object 属性值
	 */

	public static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			System.out.println("属性不存在");
			return null;
		}
	}
	   
	   /** 
	    * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list 
	    * */  
	   @SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private List getFiledsInfo(Object o){  
	    Field[] fields=o.getClass().getDeclaredFields();  
	        String[] fieldNames=new String[fields.length];  
	        List list = new ArrayList();  
	        Map infoMap=null;  
	    for(int i=0;i<fields.length;i++){  
	        infoMap = new HashMap();  
	        infoMap.put("type", fields[i].getType().toString());  
	        infoMap.put("name", fields[i].getName());  
	        infoMap.put("value", getFieldValueByName(fields[i].getName(), o));  
	        list.add(infoMap);  
	    }  
	    return list;  
	   }  
	   
	   /** 
	    * 获取  属性名(name)， 属性类型(type)，的map组成的list 
	    * */  
	   public static List<Map<String,String>> getFiledsNameAndType(Object o){  
		   
		   	Field[] fields=o.getClass().getDeclaredFields();  
	        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
	        Map<String,String> infoMap=null;  
	    for(int i=0;i<fields.length;i++){  
	        infoMap = new HashMap<String,String>();  
	        infoMap.put(fields[i].getName(),fields[i].getType().toString());  
	        list.add(infoMap);  
	    }  
	    return list;  
	   }  
	   
	     
	   /** 
	    * 获取对象的所有属性值，返回一个对象数组 
	    * */  
	   @SuppressWarnings("static-access")
	public Object[] getFiledValues(Object o){  
	    String[] fieldNames=this.getFiledName(o);  
	    Object[] value=new Object[fieldNames.length];  
	    for(int i=0;i<fieldNames.length;i++){  
	        value[i]=this.getFieldValueByName(fieldNames[i], o);  
	    }  
	    return value;  
	   }  

}
