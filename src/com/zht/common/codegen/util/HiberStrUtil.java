/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.util;

/*
 *    
	@Null   被注释的元素必须为 null  
	@NotNull    被注释的元素必须不为 null  
	@AssertTrue     被注释的元素必须为 true  
	@AssertFalse    被注释的元素必须为 false  
	@Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值  
	@Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值  
	@DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值  
	@DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值  
	@Size(max=, min=)   被注释的元素的大小必须在指定的范围内  
	@Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内  
	@Past   被注释的元素必须是一个过去的日期  
	@Future     被注释的元素必须是一个将来的日期  
	@Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式  
	  
	Hibernate Validator 附加的 constraint  
	@NotBlank(message =)   验证字符串非null，且长度必须大于0  
	@Email  被注释的元素必须是电子邮箱地址  
	@Length(min=,max=)  被注释的字符串的大小必须在指定的范围内  
	@NotEmpty   被注释的字符串的必须非空  
	@Range(min=,max=,message=)  被注释的元素必须在合适的范围内
 */


import org.apache.commons.lang3.StringUtils;
import org.zht.framework.util.ZStrUtil;

import com.zht.common.codegen.model.GenEntityProperty;

public class HiberStrUtil {

	public static void handlerProperty_String( GenEntityProperty prop){StringBuffer hiberColumnStr=new StringBuffer("");
		StringBuffer hiberValidateStr=new StringBuffer("");
		
		String clumnname=prop.getColumnName();
		Boolean nullAble=prop.getNullable();
		Boolean unique=prop.getUnique();
		Integer length=prop.getLength();
		//-------hiberValidateStr---------
		Integer minLen=prop.getMinLength();
		Integer maxLen=prop.getMaxLength();
		String lenvali="";
		String minl="";
		String maxl="";
		String isBlank="";
		if(minLen!=null){
			minl="min="+minLen+"";
			if(minLen>0){
				hiberValidateStr.append("@org.hibernate.validator.constraints.NotBlank\r\t");;
			}
		}
		if(maxLen!=null){
			maxl="max="+maxLen+"";
		}
		lenvali=minl+","+maxl;
		if(lenvali.endsWith(",")){
		   lenvali=StringUtils.substringBeforeLast(lenvali, ",");
		}
		if(lenvali.length()>0){
			hiberValidateStr.append("@org.hibernate.validator.constraints.Length("+lenvali+")\r\t");
		}
		
		if(nullAble!=null&&nullAble==false){
			hiberValidateStr.append("@javax.validation.constraints.NotNull \r\t");
			hiberValidateStr.append("@org.hibernate.validator.constraints.NotBlank\r\t");;
		}
		if(isBlank.length()>0){
			
		}
		//-------hiberValidateStr---------
		
		hiberColumnStr.append("name = \""+clumnname+"\",");
		
		if(unique!=null){
			hiberColumnStr.append("unique = "+unique+",");
		}
		if(nullAble!=null){
			hiberColumnStr.append("nullable = "+nullAble+",");
		}
		if(length!=null){
			hiberColumnStr.append("length = "+length+",");
		}else {
			length=255;
			hiberColumnStr.append("length = "+length+",");
		}
		
		String tempStr=hiberColumnStr.toString().trim();
		
		if(tempStr.endsWith(",")){
			tempStr=StringUtils.substringBeforeLast(tempStr, ",");
		}
		if(tempStr!=null&&tempStr.length()>0){
			tempStr="@javax.persistence.Column("+tempStr+")";
		}
		prop.setGeneratedHibernateModelOfPropertyStr(hiberValidateStr+tempStr);
	
	}

//    
	public static void handlerProperty_Long$Integer$Short$Byte(GenEntityProperty prop) {
		StringBuffer hiberColumnStr=new StringBuffer("");
		StringBuffer hiberValidateStr=new StringBuffer("");
		
		
		String clumnname=prop.getColumnName();
		Boolean nullAble=prop.getNullable();
		Boolean unique=prop.getUnique();
		//-------hiberValidateStr---------
		Double min=prop.getMinValue();
		Double max=prop.getMaxValue();
		if(min!=null){
			hiberValidateStr.append("@javax.validation.constraints.Min("+Math.round(min)+") \r\t");
		}
		if(max!=null){
			hiberValidateStr.append("@javax.validation.constraints.Max("+Math.round(max)+") \r\t");
		}
		if(nullAble!=null&&nullAble==false){
			hiberValidateStr.append("@javax.validation.constraints.NotNull \r\t");
		}
		//-------hiberValidateStr---------
		
		hiberColumnStr.append("name = \""+clumnname+"\",");
		
		if(unique!=null){
			hiberColumnStr.append("unique = "+unique+",");
		}
		if(nullAble!=null){
			hiberColumnStr.append("nullable = "+nullAble+",");
		}
		String sd=hiberColumnStr.toString().trim();
		if(sd.endsWith(",")){
			sd=StringUtils.substringBeforeLast(sd, ",");
		}
		sd="@javax.persistence.Column("+sd+")";
		sd=hiberValidateStr+sd;
		prop.setGeneratedHibernateModelOfPropertyStr(sd);
	}
	
	public static void handlerProperty_Double$Float$BigDecimal(GenEntityProperty prop) {

		StringBuffer hiberColumnStr=new StringBuffer("");
		StringBuffer hiberValidateStr=new StringBuffer("");
		
		String clumnName=prop.getColumnName();
		Integer precision = prop.getPrecision();
		Integer scale = prop.getScale();
		
		Boolean nullAble=prop.getNullable();
		Boolean unique=prop.getUnique();
		
		//-------hiberValidateStr---------
		Double min=prop.getMinValue();
		Double max=prop.getMaxValue();
		
		if(min!=null){
			hiberValidateStr.append("@javax.validation.constraints.DecimalMin(\""+min+"\") \r\t");
		}
		if(max!=null){
			hiberValidateStr.append("@javax.validation.constraints.DecimalMax(\""+max+"\") \r\t");
		}
		if(nullAble!=null&&nullAble==false){
			hiberValidateStr.append("@javax.validation.constraints.NotNull \r\t");
		}
		//-------hiberValidateStr---------
		hiberColumnStr.append("name = \""+clumnName+"\",");
		if(precision!=null){
			hiberColumnStr.append("precision = "+precision+",");
		}
		if(scale!=null){
			hiberColumnStr.append("scale = "+scale+",");
		}
		
		if(unique!=null){
			hiberColumnStr.append("unique = "+unique+",");
		}
		if(nullAble!=null){
			hiberColumnStr.append("nullable = "+nullAble+",");
		}
		String sd=hiberColumnStr.toString().trim();
		if(sd.endsWith(",")){
			sd=StringUtils.substringBeforeLast(sd, ",");
		}
		sd="@javax.persistence.Column("+sd+")";
		
		prop.setGeneratedHibernateModelOfPropertyStr(hiberValidateStr+sd);
		
	}
	//@Column(name = "enabled",nullable = false )
	public static void handlerProperty_Boolean(GenEntityProperty prop) {
		StringBuffer hiberAnnStr=new StringBuffer("");
		String clumnname=prop.getColumnName();
		Boolean nullAble=prop.getNullable();
		if(nullAble!=null&&nullAble==false){
			hiberAnnStr.append("@javax.validation.constraints.NotNull \r\t");
		}
		hiberAnnStr.append("@javax.persistence.Column(name = \""+clumnname+"\",nullable = "+nullAble+" )");
		prop.setGeneratedHibernateModelOfPropertyStr(hiberAnnStr.toString());
	}
	public static void handlerProperty_Bytes(GenEntityProperty prop) {
		StringBuffer hiberAnnStr=new StringBuffer("");
		String clumnname=prop.getColumnName();
		Boolean nullAble=prop.getNullable();
		if(nullAble!=null&&nullAble==false){
			hiberAnnStr.append("@javax.validation.constraints.NotNull \r\t");
		}
		hiberAnnStr.append("@javax.persistence.Column(name = \""+clumnname+"\",nullable = "+nullAble+" )");
		prop.setGeneratedHibernateModelOfPropertyStr(hiberAnnStr.toString());
	}
	
//	@javax.validation.constraints.Future
//	@javax.validation.constraints.Past
//	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
//	@javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
//	@javax.persistence.Column(name = "z_date")
//	private java.util.Date zDate;
//	
//	@javax.validation.constraints.Future
//	@javax.validation.constraints.Past
//	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
//	@javax.persistence.Column(name = "z_dateTime")
//	private java.util.Date zDateTime;
//	
//	@com.zht.framework.core.annos.CurrentTimeStamp
//	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
//	@javax.persistence.Column(name = "z_timeStamp")
//	private java.util.Date zTimeStamp;
	
	//@Past   被注释的元素必须是一个过去的日期   
	//@Future     被注释的元素必须是一个将来的日期   
	public static void handlerProperty_Date(GenEntityProperty prop){
		StringBuffer hibModelStr=new StringBuffer("");
		String editType=prop.getEditType();
		String sft="";
		String timeType="";
		String timeScope="";
		Boolean isFrutrue=prop.getIsDateFutrue();
		Boolean isPost=prop.getIsDatePost();
		if(isFrutrue!=null&&!(isFrutrue.equals(isPost))){
			if(isFrutrue){
				timeScope+="@javax.validation.constraints.Future\r\t";
			}
		}
		if(isPost!=null&&!(isPost.equals(isFrutrue))){
			if(isPost){
				timeScope+="@javax.validation.constraints.Past\r\t";
			}
		}
		
		if("date".equals(editType)){
			sft="@org.springframework.format.annotation.DateTimeFormat(pattern = \"yyyy-MM-dd\")";
			timeType="@javax.persistence.Temporal(javax.persistence.TemporalType.DATE)";
		}else if("time".equals(editType)){
			sft="@org.springframework.format.annotation.DateTimeFormat(pattern = \"HH:mm:ss\")";
			timeType="@javax.persistence.Temporal(javax.persistence.TemporalType.TIME)";
		}else if("datetime".equals(editType)){
			sft="@org.springframework.format.annotation.DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")";
			timeType="@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)";
		}else if("autoCurrentTime".equals(editType)){
			//sft="@org.springframework.format.annotation.DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")";
			timeType="@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)";
			hibModelStr.append("@org.zht.framework.annos.CurrentTimeStamp");
		}
		String clumnname=prop.getColumnName();
		hibModelStr.append(timeScope).append(sft).append("\r\t").append(timeType).append("\r\t");
		hibModelStr.append("@javax.persistence.Column(name = \""+clumnname+"\")");
		prop.setGeneratedHibernateModelOfPropertyStr(hibModelStr.toString());
		
	}
	
	//@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL, mappedBy="parentRbacGroup")
	public static void handlerOnetomany( GenEntityProperty genEntityProperty){
		String hiberStr="";
		String onetomanyManpedBy=genEntityProperty.getOneTomanyMappedBy();
		hiberStr="@javax.persistence.OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL, mappedBy=\""+onetomanyManpedBy+"\")";//实体类名的
		genEntityProperty.setGeneratedHibernateModelOfPropertyStr(hiberStr);
	}
	public static void handlerManytoOne( GenEntityProperty genEntityProperty){
		
		String columnName = genEntityProperty.getColumnName();
		String hiberStr="@javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.LAZY)\r\t";
		hiberStr+="@javax.persistence.JoinColumn(name=\""+columnName+"\")";
		Boolean isTree=genEntityProperty.getGenEntity().getIsTree();
		if(isTree!=null&&isTree){
			Boolean isTreeProperty=genEntityProperty.getIsTreeProperty();
			if(isTreeProperty!=null&&isTreeProperty&isTree){
				hiberStr+="\r\t@org.zht.framework.annos.TreeParentFied()";
			}
		}
		genEntityProperty.setGeneratedHibernateModelOfPropertyStr(hiberStr);
	}

	public static void handlerManytoMany(GenEntityProperty targetProperty) {
		String hiberStr="";
		if("manytomanyMarster".equals(targetProperty.getRelationType())){
				String manytomanyMappedBy=targetProperty.getManytomanyMappedBy();
				hiberStr="@javax.persistence.ManyToMany(cascade=javax.persistence.CascadeType.ALL,mappedBy=\""+manytomanyMappedBy+"\")";
		}else if("manytomanySlaver".equals(targetProperty.getRelationType())){
			String joinTable=targetProperty.getManytomanyJoinTable();
			String colM=targetProperty.getManytomanyJoinColumnSelf();
			String colS =targetProperty.getManytomanyJoinColumnOpposite();
			 hiberStr="@javax.persistence.ManyToMany(fetch=javax.persistence.FetchType.LAZY)\r\t";
				hiberStr+="@javax.persistence.JoinTable(name=\""+joinTable+"\", joinColumns = {\r\t";
				hiberStr+="\t@javax.persistence.JoinColumn(name=\""+colM+"\", nullable=false, updatable=false) },\r\t";
				hiberStr+="\tinverseJoinColumns = { @javax.persistence.JoinColumn(name=\""+colS+"\", nullable=false, updatable=false) })";
				targetProperty.setGeneratedHibernateModelOfPropertyStr(hiberStr);
		}
		targetProperty.setGeneratedHibernateModelOfPropertyStr(hiberStr);
	}
	
//	@OneToOne(mappedBy="husbandFkDouble")
//	public WifeFkDouble getWifeFkDouble() {
//		return wifeFkDouble;
//	}
//	 @OneToOne(cascade=CascadeType.ALL)
//	    @JoinColumn(name="husband_id")
//		public HusbandFkDouble getHusbandFkDouble() {
//			return husbandFkDouble;
//		}
	public static void handlerOneToOne(GenEntityProperty targetProperty) {
		//只有主表有mappedby，，以此判断是从表还是主表
		String hiberStr="";
		String marsterMapedBy=targetProperty.getOneToOneMappedBy();
		if(ZStrUtil.isEmptyAfterTrimE(marsterMapedBy)){//从表
			hiberStr="@javax.persistence.OneToOne(cascade=javax.persistence.CascadeType.ALL)\r\t";
			String columnName = targetProperty.getColumnName();
			hiberStr+="@javax.persistence.JoinColumn(name=\""+columnName+"\")";
			
		}else{
			hiberStr="@javax.persistence.OneToOne(mappedBy=\""+marsterMapedBy+"\")";
		}
		targetProperty.setGeneratedHibernateModelOfPropertyStr(hiberStr);
	}
}
