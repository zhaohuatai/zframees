/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.annos;



public class AnnTest {

	public static void main(String[] args) {
		String sdsd="{adasdsa-asdsad}{sdasd-asdsad}{sdasd-asdsa}";
		sdsd=sdsd.replace("{", "");
		String[]sd=sdsd.split("}");
		for(String f:sd){
			 System.out.println(f);
		 }
	}
//	 private static void generateCurrentTimeStamp(Object m){
//		 Field[] fields=m.getClass().getDeclaredFields();
//		 if(fields==null||fields.length==0){
//			 return;
//		 }
////		 for(Field f:fields){
////			 System.out.println(f.getType());
////		 }
////		List<Field> fList=Arrays.asList(fields);
////		
////		 if(!(fList.contains(java.sql.Date.class)||fList.contains(java.util.Date.class))){
////			 return;
////		 }
//		 for(Field f:fields){
//			 if(f.isAnnotationPresent(CurrentTimeStamp.class)){
//				 MethodAccess access = MethodAccess.get(m.getClass());
//				 access.invoke(m, "set"+ZStrUtil.toUpCaseFirst(f.getName()), new Date(System.currentTimeMillis()));  
//			 }
//		 }
//	 }
}
