/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.util;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.zht.framework.data.DataSet;
public class ZBeanUtil extends org.springframework.beans.BeanUtils{
	   
	
	public static boolean isEmptyValue(Object value){
		if(value==null){
			return true;
		}else if(value instanceof String){
			return "".equals(String.valueOf(value).trim());
		}else if(value instanceof Collection){
			return ((Collection<?>)value).size()==0;
		}else if(value.getClass().isArray()){
			boolean b=false;
			try{
				b=((Collection[])value).length==0;
			}catch(Exception e){
				b=Arrays.asList(value).size()==0;
			}
			return b;
		}else{
			return false;
		}
	}
	
	public static Long parseLong(Object value){
		try{
			return Long.valueOf(""+value);
		}catch(Exception e){
			return null;
		}
	}
	public static Long parseLong(Object value,Long defaultVlaue){
		try{
			return Long.valueOf(""+value);
		}catch(Exception e){
			return defaultVlaue;
		}
	}
	public static Integer parseInteger(Object value){
		try{
			return Integer.valueOf(""+value);
		}catch(Exception e){
			return null;
		}
	}
	public static Integer parseInteger(Object value,Integer defaultVlaue){
		try{
			return Integer.valueOf(""+value);
		}catch(Exception e){
			return defaultVlaue;
		}
	}
	public static Boolean parseBoolean(Object value){
		try{
			return Boolean.parseBoolean((String) value);
		}catch(Exception e){
			return null;
		}
	}
	public static Boolean parseBoolean(Object value,Boolean defaultVlaue){
		try{
			return Boolean.parseBoolean((String) value);
		}catch(Exception e){
			return defaultVlaue;
		}
	}
	
	public static void copy(Object source, Object target,Boolean ignorNull)
			throws BeansException{
		copy(source, target,ignorNull, null, (String[]) null);
	}
	
	private static void copy(Object source, Object target,Boolean ignorNull, Class<?> editable, String... ignoreProperties)
			throws BeansException {

		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		Class<?> actualEditable = target.getClass();
		if (editable != null) {
			if (!editable.isInstance(target)) {
				throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
						"] not assignable to Editable class [" + editable.getName() + "]");
			}
			actualEditable = editable;
		}
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null &&
							ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							if(ignorNull!=null&&ignorNull){
								if(value!=null&&(!"[]".equals(value.toString()))){//空对象 或者空集合
									if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
										writeMethod.setAccessible(true);
									}
									writeMethod.invoke(target, value);
								}
							}else{
								if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
									writeMethod.setAccessible(true);
								}
								writeMethod.invoke(target, value);
							}
							
						}
						catch (Throwable ex) {
							throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<?> removeDuplicateWithOrder(List list) {
		if(list==null||list.size()==0){
			return list;
		}
		List newList = new ArrayList();
		for(Object object:list){
			if(!newList.contains(object)){
				newList.add(object);
			}
		}
        return newList;
    }
	
	 public static void main(String[] sd){
			Long[] ssd=new Long[]{1L,1L,2L,3L};
			ssd=(Long[]) removeDuplicateWithOrder(ssd);
			System.out.println(isEmptyValue(ssd));
		 }
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object[] removeDuplicateWithOrder(Object[] array) {
		if(array==null||array.length==0){
			return array;
		}
		List newList = new ArrayList();
		for (Object object : array) {
			if (!newList.contains(object)) {
				newList.add(object);
			}
		}
		Class elementType = array.getClass().getComponentType();
		Object[] newArray = (Object[]) java.lang.reflect.Array.newInstance(elementType, 0);
		array = newList.toArray(newArray);
		return array;
    }
	@SuppressWarnings("rawtypes")
	public static Object convertMapToBean(Map map, Class<?> type) {
		Object obj = null;
		try{
			BeanInfo beanInfo = null;
		
			beanInfo = Introspector.getBeanInfo(type);
			obj = type.newInstance();
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String properName = property.getName();
				if (map.containsKey(property.getName())) {
					try {// 不影响其他属性
						
					Object value = map.get(properName);
					Method setter = property.getWriteMethod();  
					setter.invoke(obj, value);  
					//Unable to find non-private method
//					access.invoke(obj,"set" + ZStrUtil.toUpCaseFirst(properName),value);
					} catch (Exception e) {
						e.printStackTrace();
					}
	
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return obj;
		}
		return obj;
	}
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map convertBeanToMap(Object bean) {
		 Map returnMap =null; 
		 try{
	        Class<?> type = bean.getClass(); 
	        BeanInfo beanInfo = Introspector.getBeanInfo(type); 
	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
//	        MethodAccess access = MethodAccess.get(type.getClass());
	        returnMap=new HashMap(); 
	    	for (PropertyDescriptor property  : propertyDescriptors) {
	    		String properName = property.getName();
	    		 Method getter = property.getReadMethod();  
	    		 Object value = getter.invoke(bean);  
	    		 returnMap.put(properName, value);  
				//一下方法报错
//				Object value=access.invoke(bean,"get" + ZStrUtil.toUpCaseFirst(properName));
//				if (value != null){
//					returnMap.put(properName, value);
//				}else{
//					returnMap.put(properName, null);
//				}
					
	    	}
		 }catch(Exception e){
			 e.printStackTrace();
			 return returnMap;
		 }
	        return returnMap; 
	}
	 
	 
	 public static void unionDataSet(DataSet source,DataSet toBeUnion){
		 if(source==null||toBeUnion==null){
			return ;
		 }
		 source.getRows().addAll(toBeUnion.getRows());
		 source.getFooter().addAll(toBeUnion.getFooter());
		 
		 long a=source.getTotal();
		 long b=toBeUnion.getTotal();
		 source.setTotal(new Long(a+b));
		 source.setSimpleFooter(source.getSimpleFooter()+""+toBeUnion.getSimpleFooter());
		 
	 }

}
