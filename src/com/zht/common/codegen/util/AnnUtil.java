/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.zht.framework.util.ZStrUtil;

public class AnnUtil {
	public static Annotation[] anylerAnn(Class<?> clazz,Field field){
		String getFieldStr="get"+ZStrUtil.toUpCaseFirst(field.getName());
		Annotation[] annos =null;
		try {
			 annos = clazz.getMethod(getFieldStr).getAnnotations();
			if(annos==null||annos.length==0){
				annos=field.getAnnotations();
				if(annos==null||annos.length==0){
					return null;
				}
    		}
		} catch (NoSuchMethodException e) {
			return null;
		} catch (SecurityException e) {
			return null;
		}
		return annos;
	}
	
	/**
	 * 
	 * @param clzz 
	 * @param property
	 * @return filed ,relationType
	 */
	public static  Map<String,String> getRelationType(Class<?> calzz){
		Map<String,String> map=new HashMap<String, String>();
		Field[] fields=calzz.getDeclaredFields();
		for (Field field : fields) {
			if ("serialVersionUID".equalsIgnoreCase(field.getName())) {
				continue;
			}
			Annotation[] annos = anylerAnn(calzz, field);
			if(annos==null||annos.length==0){
				continue;
			}
			for(Annotation ann:annos){
				if(ann instanceof OneToOne){
					if(!map.containsKey(field.getName())){
						map.put(field.getName(), "OneToOne");
					}
				}else if(ann instanceof OneToMany){
					if(!map.containsKey(field.getName())){
						map.put(field.getName(), "OneToMany");
					}
				}else if(ann instanceof ManyToOne){
					if(!map.containsKey(field.getName())){
						map.put(field.getName(), "ManyToOne");
					}
				}else if(ann instanceof ManyToMany){
					if(!map.containsKey(field.getName())){
						map.put(field.getName(), "ManyToMany");
					}
				}else{
					if(!map.containsKey(field.getName())){
						map.put(field.getName(), "property");
					}
					}
				}
			}
		return map;
	}
}
