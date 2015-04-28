/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.util;

import org.apache.commons.lang3.StringUtils;
import org.zht.framework.util.ZStrUtil;

import com.zht.common.codegen.model.GenEntityProperty;



public class UIEasyStrUtil {
	
	public static void handlerProperty_String( GenEntityProperty prop){
		StringBuffer displayStr=new StringBuffer("");
		StringBuffer editStr=new StringBuffer("");
		StringBuffer queryStr=new StringBuffer("");
		String entitysimplName=prop.getGenEntity().getName();
		entitysimplName=ZStrUtil.substringAfterLast(entitysimplName, ".");
		entitysimplName=ZStrUtil.toLowerCaseFirst(entitysimplName);
		displayStr.append("{field:'"+prop.getPropertyName()+"',title:'"+prop.getDisplay()+"',width:"+prop.getColumnWidth()+",");
		String simpleFormat=prop.getSimpleFormat();
		if(simpleFormat!=null&&simpleFormat.length()>0){
			 displayStr.append(simpleFormat);
		}
		displayStr.append("},");
		String editType=prop.getEditType();
			if(editType==null){
				return ;
			}
		if("text".equals(editType)){
			String data_options=" ";
			editStr.append("<input type=\"text\" name=\""+prop.getPropertyName()+"\" value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\"").append(" ");
			editStr.append("class=\"easyui-textbox validatebox\"").append(" ");
			if(prop.getNullable()!=null&&prop.getNullable()==false){
				data_options+="required:true,";
			}
				
			String validateType =prop.getValidateType();
			if(validateType==null||"".equals(validateType)){
					Integer lenMin=prop.getMinLength();
					Integer lenMax=prop.getMaxLength();
					if(lenMax!=null&&lenMax>0&&lenMin>=0){
						data_options+=" validType:'length["+lenMin+","+lenMax+"]'";
					}
				}else{
					data_options+=" validType:'"+validateType+"'";
				}
				editStr.append(" data-options=\""+data_options+"\"");
				
				editStr.append("/>");
				queryStr.append(editStr.toString().replace(" data-options=\""+data_options+"\"", " ")
						.replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" ")
						.replace("value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\"", " "));
			}else if("textarea".equals(editType)){
				String data_options=" ";
				editStr=new StringBuffer("<textarea name=\""+prop.getPropertyName()+"\" value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\" ");
				String content=ZStrUtil.trimToEmpty(prop.getEditContent());
				if(content.length()>0){//rows:3,cols:20{rows-3}{cols-20}
					content=content.replace("{", "");
        			String[] oneEContent=content.split("}");
        			if(oneEContent!=null&&oneEContent.length>=2){
        				String row="";
            			for(String str:oneEContent){
            				String[] contents=str.split("-");
            				String rows=contents[0].trim();
            				String value=contents[1].trim();
            				 row+=rows+"=\""+value+"\" ";
            			}
            			editStr.append(row);
            		}
				}
				
				editStr.append("class=\"easyui-textbox validatebox\" ");
				if(prop.getNullable()!=null&&prop.getNullable()==false){
					data_options+="required:true,";
				}
				String validateType =prop.getValidateType();
				if(validateType==null||"".equals(validateType)){
					Integer lenMin=prop.getMinLength();
					Integer lenMax=prop.getMaxLength();
					if(lenMax!=null&&lenMax>0&&lenMin>0){
						data_options+="validType:'length["+lenMin+","+lenMax+"]'";
					}
				}
				editStr.append("data-options=\""+data_options+"\"");
				editStr.append("></textarea>");
				queryStr.append(editStr.append(editStr.toString().replace(" data-options=\""+data_options+"\"", " ")
						.replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" ")
						.replace("value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\"", " ")));
			}else if("password".equals(editType)){
				String data_options=" ";
				editStr=new StringBuffer("<input type=\"password\" name=\""+prop.getPropertyName()+"\" value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\" ");
				editStr.append("class=\"easyui-textbox validatebox\" ");
				 if(prop.getNullable()!=null&&prop.getNullable()==false){
					data_options+="required:true,";
				 }
				 	Integer lenMin=prop.getMinLength();
					Integer lenMax=prop.getMaxLength();
					if(lenMax!=null&&lenMax>0&&lenMin>0){
						data_options+="validType:'length["+lenMin+","+lenMax+"]'";
					}
				 editStr.append("data-options=\""+data_options+"\"");
				 editStr.append("/>");
				 queryStr.append(editStr.append(editStr.toString().replace(" data-options=\""+data_options+"\"", " ")
							.replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" ")));
			}else if("combobox".equals(editType)){
				String data_options=" ";
				editStr=new StringBuffer("<input type=\"text\" name=\""+prop.getPropertyName()+"\" value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\" ");
				editStr.append( " class=\"easyui-combobox\" ");
				 if(prop.getNullable()!=null&&prop.getNullable()==false){
						data_options+="required:true,";
				 }
				 String content=prop.getEditContent().trim().replace("{", "").replace("}", "");
		    		String[] sdasd=content.split(",");
		    		String url=sdasd[0].replace("url:", "");
		    		String valueField=sdasd[1].replace("valueField:", "");
		    		String textField=sdasd[2].replace("textField:", "");
		    		String options="url:'${ctx}"+url+"',method:'post',valueField:'"+valueField+"',textField:'"+textField+"'";
				 data_options+=options;
				 
				 editStr.append(" data-options=\""+data_options+"\"");
				 editStr.append("/>");
				 queryStr.append(editStr.toString().replace("required:true,", " ")
						 .replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" ")
						 .replace("value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\"", " "));
				 
			}else if("combotree".equals(editType)){
				String data_options=" ";
				editStr=new StringBuffer("<input type=\"text\" name=\""+prop.getPropertyName()+"\" value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\" ");
				editStr.append( " class=\"easyui-combotree\" ");
				 if(prop.getNullable()!=null&&prop.getNullable()==false){
						data_options+="required:true,";
				 }
				 String content=prop.getEditContent().trim().replace("{", "").replace("}", "");
		    		String[] sdasd=content.split(",");
		    		String url=sdasd[0].replace("url:", "");
		    		String valueField=sdasd[1].replace("valueField:", "");
		    		String textField=sdasd[2].replace("textField:", "");
		    		String options="url:'${ctx}"+url+"',method:'post',valueField:'"+valueField+"',textField:'"+textField+"'";
				 data_options+=options;
				 
				 editStr.append(" data-options=\""+data_options+"\"");
				 editStr.append("/>");
				 queryStr.append(editStr.toString().replace("required:true,", " ")
						 .replace("name=\""+prop.getPropertyName()+"\" ", "name=\"webParams["+prop.getPropertyName()+"]\" ")
						 .replace("value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\"", " "));
				 
			}else if("select".equals(editType)){
				// {北京-1}{上海-2}{深圳-3}
				String data_options=" ";
				//value=\"${"+genEntityDetail.getPropertyName()+"}\"editStr.append("\r\t");
				editStr=new StringBuffer("\r\t<select  name=\""+prop.getPropertyName()+"\" class=\"easyui-combobox\" style=\"width: 170px;\" ");
				 if(prop.getNullable()!=null&&prop.getNullable()==false){
					data_options+="required:true";
					editStr.append(" data-options=\""+data_options+"\"");
				 }
				 editStr.append(">\r\t");
				// finalEditContent.append("\r\n");
				 String con=prop.getEditContent();
				 if(con!=null&&con.length()>0){
					 con=con.trim().replace("{", "");
	        			String[] oneEContent=con.split("}");
	        			if(oneEContent!=null&&oneEContent.length>0){
	            			for(String str:oneEContent){
	            				String[] content=str.split("-");
	            				String value=content[0].trim();
	            				String display=content[1].trim();
	            				editStr.append("<option value=\""+value+"\" <c:if test=\"${"+entitysimplName+"."+prop.getPropertyName()+" eq '"+value+"' }\">selected='selected' </c:if> >"+display+"</option>").append("\r\t");
	            				//queryStr.append("<option value=\""+value+"\">"+display+"</option>").append("\r\t");
	            			}
	            		}
	        		}
				 editStr.append("</select>");
				 queryStr.append(editStr.toString().replace(data_options, " ")
						 .replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" "));
				 
				 editStr.append("\r\t");
				 queryStr.append("\r\t");
			}else if("checkbox".equals(editType)){
				//.....(value1-display1-lable)
				//{value-display}
				String data_options=" ";
				 String con=prop.getEditContent();
				 if(con!=null&&con.length()>0){
					 editStr.append("\r\t");
					 	con=con.replace("{", "");
	        			String[] oneEContent=con.split("}");
	        			if(oneEContent!=null&&oneEContent.length>0){
	            			for(String str:oneEContent){
	            				String[] content=str.split("-");
	            				String value=content[0].trim();
	            				String display=content[1].trim();
	            				editStr.append("<input type=\"checkbox\"  name=\""+prop.getPropertyName()+"\" value=\""+value+"\" <c:if test=\"${"+entitysimplName+"."+prop.getPropertyName()+" eq '"+value+"' }\">checked=\"checked\" </c:if> >"+display+"").append("\r\t");
	            			}
	            		}
	        		}
				 queryStr.append(editStr.toString().replace(data_options, " ")
						 .replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" "));
			}else if("lookup".equals(editType)){}
			if(prop.getIsQueryCondtion()){
				prop.setGeneratedQueryOfPropertyStr(queryStr.toString());
			}
			prop.setGeneratedListDisplayOfPropertyStr(displayStr.toString());
			prop.setGeneratedEditOfPropertyStr(editStr.toString());
		
	}
//text  combox   select lookup
	public static void handlerProperty_Long$Integer$Short$Byte(GenEntityProperty prop) {

		StringBuffer editStr=new StringBuffer("");
		StringBuffer queryStr=new StringBuffer("");
		StringBuffer displayStr=new StringBuffer("");
		
		String entitysimplName=prop.getGenEntity().getName();
		entitysimplName=ZStrUtil.substringAfterLast(entitysimplName, ".");
		entitysimplName=ZStrUtil.toLowerCaseFirst(entitysimplName);
		
		displayStr.append("{field:'"+prop.getPropertyName()+"',title:'"+prop.getDisplay()+"',width:"+prop.getColumnWidth()+",");
		String simpleFormat=prop.getSimpleFormat();
		if(simpleFormat!=null&&simpleFormat.length()>0){
			 displayStr.append(simpleFormat);
		}
		displayStr.append("},");
		prop.setGeneratedListDisplayOfPropertyStr(displayStr.toString());
		
		String editType=prop.getEditType();
		
		if("text".equals(editType)){
			String data_options=" ";
			editStr.append("<input type=\"text\" name=\""+prop.getPropertyName()+"\" value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\"").append(" ");
			editStr.append("class=\"easyui-numberbox validatebox\"").append(" ");
			if(prop.getNullable()!=null&&prop.getNullable()==false){
				data_options+="required:true,";
			}
			Double min=prop.getMinValue();
			Double max=prop.getMaxValue();
			if(min!=null){
				data_options+="min:"+min+",";
			}
			if(max!=null){
				data_options+="max:"+max+" ";
			}
			editStr.append(" data-options=\""+data_options+"\"");
			editStr.append("/>");
			queryStr.append(editStr.toString().replace(" data-options=\""+data_options+"\"", " ")
					 .replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" "));
			
		}else if("combox".equals(editType)){
			String data_options=" ";
			editStr=new StringBuffer("<input type=\"text\" name=\""+prop.getPropertyName()+"\" value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\" ");
			editStr.append( " class=\"easyui-combox\" ");
			 if(prop.getNullable()!=null&&prop.getNullable()==false){
					data_options+="required:true,";
			 }
			 String content=prop.getEditContent().trim().replace("{", "").replace("}", "");
	    		String[] sdasd=content.split(",");
	    		String url=sdasd[0].replace("url:", "");
	    		String valueField=sdasd[1].replace("valueField:", "");
	    		String textField=sdasd[2].replace("textField:", "");
	    		String options="url:'${ctx}"+url+"',method:'post',valueField:'"+valueField+"',textField:'"+textField+"'";
			 data_options+=options;
			 
			 editStr.append(" data-options=\""+data_options+"\"");
			 editStr.append("/>");
			 queryStr.append(editStr.toString().replace("required:true,", " ")
					 .replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" ")
					 .replace("value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\"", " "));
			
		}else if("select".equals(editType)){
			// {北京-1}{上海-2}{深圳-3}
			String data_options=" ";
			//value=\"${"+genEntityDetail.getPropertyName()+"}\"editStr.append("\r\t");
			editStr=new StringBuffer("\r\t<select  name=\""+prop.getPropertyName()+"\" class=\"easyui-combobox\"  style=\"width: 170px;\"  ");
			 if(prop.getNullable()!=null&&prop.getNullable()==false){
				data_options+="required:true";
				editStr.append(" data-options=\""+data_options+"\"");
			 }
			 editStr.append(">\r\t");
			// finalEditContent.append("\r\n");
			 String con=prop.getEditContent();
			 if(con!=null&&con.length()>0){
				 con=con.trim().replace("{", "");
        			String[] oneEContent=con.split("}");
        			if(oneEContent!=null&&oneEContent.length>0){
            			for(String str:oneEContent){
            				String[] content=str.split("-");
            				String value=content[0].trim();
            				String display=content[1].trim();
            				editStr.append("<option value=\""+value+"\" <c:if test=\"${"+entitysimplName+"."+prop.getPropertyName()+" eq '"+value+"' }\">selected='selected' </c:if> >"+display+"</option>").append("\r\t");
            				queryStr.append("<option value=\""+value+"\">"+display+"</option>").append("\r\t");
            			}
            		}
        		}
			 editStr.append("</select>");
			 queryStr.append(editStr.toString().replace(data_options, " ")
			.replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" "));
			 editStr.append("\r\t");
			 queryStr.append("\r\t");
		}else if("lookup".equals(editType)){}
		if(prop.getIsQueryCondtion()){
			prop.setGeneratedQueryOfPropertyStr(queryStr.toString());
		}
		prop.setGeneratedEditOfPropertyStr(editStr.toString());
	}
	public static void handlerProperty_Double$Float$BigDecimal(GenEntityProperty prop) {
			StringBuffer editStr=new StringBuffer("");
			StringBuffer queryStr=new StringBuffer("");
			StringBuffer displayStr=new StringBuffer("");
			
			String entitysimplName=prop.getGenEntity().getName();
			entitysimplName=ZStrUtil.substringAfterLast(entitysimplName, ".");
			entitysimplName=ZStrUtil.toLowerCaseFirst(entitysimplName);
			
			displayStr.append("{field:'"+prop.getPropertyName()+"',title:'"+prop.getDisplay()+"',width:"+prop.getColumnWidth()+",");
			String simpleFormat=prop.getSimpleFormat();
			if(simpleFormat!=null&&simpleFormat.length()>0){
				 displayStr.append(simpleFormat);
			}
			displayStr.append("},");
			prop.setGeneratedListDisplayOfPropertyStr(displayStr.toString());
			
			String editType=prop.getEditType();
			
			if("text".equals(editType)){
				String data_options=" ";
				editStr.append("<input type=\"text\" name=\""+prop.getPropertyName()+"\" value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\"").append(" ");
				editStr.append("class=\"easyui-numberbox validatebox\"").append(" ");
				if(prop.getNullable()!=null&&prop.getNullable()==false){
					data_options+="required:true,";
				}
				Double min=prop.getMinValue();
				Double max=prop.getMaxValue();
				if(min!=null){
					data_options+="min:"+min+",";
				}
				if(max!=null){
					data_options+="max:"+max+",";
				}
				//Integer precision =prop.getPrecision();
				Integer scale  =prop.getScale();
//				if(precision!=null&&precision>0){
//					data_options+="min:"+min+",";
//				}
				if(scale!=null&&scale>0){
					data_options+="scale:"+scale+" ";
				}
				editStr.append(" data-options=\""+data_options+"\"");
				editStr.append("/>");
				queryStr.append(editStr.toString().replace(" data-options=\""+data_options+"\"", " ")
						.replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" "));
			}else if("combox".equals(editType)){
				String data_options=" ";
				editStr=new StringBuffer("<input type=\"text\" name=\""+prop.getPropertyName()+"\" value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\" ");
				editStr.append( " class=\"easyui-combox\" ");
				 if(prop.getNullable()!=null&&prop.getNullable()==false){
						data_options+="required:true,";
				 }
				 String content=prop.getEditContent().trim().replace("{", "").replace("}", "");
		    		String[] sdasd=content.split(",");
		    		String url=sdasd[0].replace("url:", "");
		    		String valueField=sdasd[1].replace("valueField:", "");
		    		String textField=sdasd[2].replace("textField:", "");
		    		String options="url:'${ctx}"+url+"',method:'post',valueField:'"+valueField+"',textField:'"+textField+"'";
				 data_options+=options;
				 
				 editStr.append(" data-options=\""+data_options+"\"");
				 editStr.append("/>");
				 queryStr.append(editStr.toString().replace("required:true,", " ")
						 .replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" ")
						 .replace("value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\"", " "));
			}else if("select".equals(editType)){
				// {北京-1}{上海-2}{深圳-3}
				String data_options=" ";
				//value=\"${"+genEntityDetail.getPropertyName()+"}\"editStr.append("\r\t");
				editStr=new StringBuffer("\r\t<select  name=\""+prop.getPropertyName()+"\" class=\"easyui-combobox\"  style=\"width: 170px;\"  ");
				 if(prop.getNullable()!=null&&prop.getNullable()==false){
					data_options+="required:true";
					editStr.append(" data-options=\""+data_options+"\"");
				 }
				 editStr.append(">\r\t");
				// finalEditContent.append("\r\n");
				 String con=prop.getEditContent();
				 if(con!=null&&con.length()>0){
					 con=con.trim().replace("{", "");
	        			String[] oneEContent=con.split("}");
	        			if(oneEContent!=null&&oneEContent.length>0){
	            			for(String str:oneEContent){
	            				String[] content=str.split("-");
	            				String value=content[0].trim();
	            				String display=content[1].trim();
	            				editStr.append("<option value=\""+value.trim()+"\" <c:if test=\"${"+entitysimplName+"."+prop.getPropertyName()+" eq '"+value+"' }\">selected='selected' </c:if> >"+display+"</option>").append("\r\t");
	            			}
	            		}
	        		}
				 editStr.append("</select>");
				 queryStr.append(editStr.toString().replace(data_options, " ")
						 .replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" "));
				 editStr.append("\r\t");
				 queryStr.append("\r\t");
			}else if("lookup".equals(editType)){}
			if(prop.getIsQueryCondtion()){
				prop.setGeneratedQueryOfPropertyStr(queryStr.toString());
			}
			prop.setGeneratedEditOfPropertyStr(editStr.toString());
	}
	//{true-是}{false-否}
	public static void handlerProperty_Boolean(GenEntityProperty prop) {
		String entityClazzName=prop.getGenEntity().getName();
		if(entityClazzName!=null&&entityClazzName.contains(".")){
			entityClazzName=StringUtils.substringAfterLast(entityClazzName,".");
			entityClazzName=ZStrUtil.toLowerCaseFirst(entityClazzName);
		}
		
		StringBuffer editStr=new StringBuffer("");
		StringBuffer queryStr=new StringBuffer("");
		StringBuffer displayStr=new StringBuffer("");
		String editType=prop.getEditType();
		if("select".equals(editType)){
				String data_options=" ";
				editStr=new StringBuffer("\r\t<select  name=\""+prop.getPropertyName()+"\" class=\"easyui-combobox\"  style=\"width: 170px;\"   ");
				 if(prop.getNullable()!=null&&prop.getNullable()==false){
					data_options+="required:true";
					editStr.append(" data-options=\""+data_options+"\"");
				 }
				 editStr.append(">").append("\r\t");
				 String con=prop.getEditContent();
				 if(con!=null&&con.length()>0){
					 con=con.trim().replace("{", "");
	        			String[] oneEContent=con.split("}");
	        			if(oneEContent!=null&&oneEContent.length==2){
	            			for(String str:oneEContent){
	            				String[] content=str.split("-");
	            				String value=content[0].trim();
	            				String display=content[1].trim();
	            				editStr.append("<option value=\""+value+"\" <c:if test=\"${"+entityClazzName+"."+prop.getPropertyName()+" eq "+value+" }\">selected='selected' </c:if> >"+display+"</option>").append("\r\t");
	            			}
	            		}
	        		}
				 editStr.append("</select>");
				 queryStr.append(editStr.toString().replace(" data-options=\""+data_options+"\"", " ")
						 .replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" "));
				 editStr.append("\r\t");
				 queryStr.append("\r\t");
				if(prop.getIsQueryCondtion()){
					prop.setGeneratedQueryOfPropertyStr(queryStr.toString());
				}
				prop.setGeneratedEditOfPropertyStr(editStr.toString());
				displayStr.append("{field:'"+prop.getPropertyName()+"',title:'"+prop.getDisplay()+"',width:"+prop.getColumnWidth()+",");
				String simpleFormat=prop.getSimpleFormat();
				 if(simpleFormat!=null&&simpleFormat.length()>0){
					 displayStr.append(simpleFormat);
				 }
				 displayStr.append("},");
				 prop.setGeneratedListDisplayOfPropertyStr(displayStr.toString());
			}

	}
	
	public static void handlerProperty_Date(GenEntityProperty prop){
		String entitysimplName=prop.getGenEntity().getName();
		if(entitysimplName!=null&&entitysimplName.contains(".")){
			entitysimplName=StringUtils.substringAfterLast(entitysimplName,".");
			entitysimplName=ZStrUtil.toLowerCaseFirst(entitysimplName);
		}
				StringBuffer editStr=new StringBuffer("");
				StringBuffer queryStr=new StringBuffer("");
				StringBuffer displayStr=new StringBuffer("");
				String calzzType="";
				String editType=prop.getEditType();
				String data_options=" ";
				if("date".equals(editType)){
					calzzType="class=\"easyui-datebox\" ";
				}else if("datetime".equals(editType)){
					calzzType="class=\"easyui-datetimebox\" ";
				}else if("autoCurrentTime".equals(editType)){
					calzzType="class=\"easyui-datetimebox\"";
				}
				editStr.append("<input type=\"text\" name=\""+prop.getPropertyName()+"\" value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\"").append(" ");
				editStr.append(calzzType).append(" ");
				
				if(prop.getNullable()!=null&&prop.getNullable()==false){
					data_options+="required:true,";
				}
				editStr.append(" data-options=\""+data_options+"\"");
				editStr.append("/>");
				queryStr.append(editStr.toString().replace(" data-options=\""+data_options+"\"", " ")
						.replace("name=\""+prop.getPropertyName()+"\"", " name=\"webParams["+prop.getPropertyName()+"]\" ")
						.replace("value=\"${"+entitysimplName+"."+prop.getPropertyName()+"}\"", " "));
			if(prop.getIsQueryCondtion()){
				prop.setGeneratedQueryOfPropertyStr(queryStr.toString());
			}
			 if("autoCurrentTime".equals(editType)){
				 editStr=new StringBuffer();
			}
			prop.setGeneratedEditOfPropertyStr(editStr.toString());
			
			displayStr.append("{field:'"+prop.getPropertyName()+"',title:'"+prop.getDisplay()+"',width:"+prop.getColumnWidth()+",");
			String simpleForamt=prop.getSimpleFormat();
			if(simpleForamt!=null&&simpleForamt.length()>0){
				displayStr.append(simpleForamt);
			}
			displayStr.append("},");
			prop.setGeneratedListDisplayOfPropertyStr(displayStr.toString());
	}
	
	public static void handlManyToOne( GenEntityProperty prop){

		StringBuffer displayStr=new StringBuffer("");
		StringBuffer editStr=new StringBuffer("");
		StringBuffer queryStr=new StringBuffer("");
		String entitysimplName=prop.getGenEntity().getName();
		entitysimplName=ZStrUtil.substringAfterLast(entitysimplName, ".");
		entitysimplName=ZStrUtil.toLowerCaseFirst(entitysimplName);
		displayStr.append("{field:'"+prop.getPropertyName()+"',title:'"+prop.getDisplay()+"',width:"+prop.getColumnWidth()+",");
		String simpleFormat=prop.getSimpleFormat();
		if(simpleFormat!=null&&simpleFormat.length()>0){
			 displayStr.append(simpleFormat);
		}
		displayStr.append("},");
		String editType=prop.getEditType();
			if(editType==null){
				return ;
			}
		 if("combobox".equals(editType)){
				String data_options=" ";
				editStr=new StringBuffer("<input type=\"text\" name=\""+prop.getPropertyName()+".id\" value=\"${"+entitysimplName+"."+prop.getPropertyName()+".id}\" ");
				editStr.append( " class=\"easyui-combobox\" ");
				 if(prop.getNullable()!=null&&prop.getNullable()==false){
						data_options+="required:true,";
				 }
				 String content=prop.getEditContent().trim().replace("{", "").replace("}", "");
		    		String[] sdasd=content.split(",");
		    		String url=sdasd[0].replace("url:", "");
		    		String valueField=sdasd[1].replace("valueField:", "");
		    		String textField=sdasd[2].replace("textField:", "");
		    		String options="url:'${ctx}"+url+"',method:'post',valueField:'"+valueField+"',textField:'"+textField+"'";
				 data_options+=options;
				 
				 editStr.append(" data-options=\""+data_options+"\"");
				 editStr.append("/>");
				 queryStr.append(editStr.toString().replace(" data-options=\""+data_options+"\"", " ")
						 .replace("name=\""+prop.getPropertyName()+".id\"", " name=\"webParams["+prop.getPropertyName()+".id]\" ")
						 .replace("value=\"${"+entitysimplName+"."+prop.getPropertyName()+".id}\"", " "));
			}else if("combotree".equals(editType)){
				String data_options=" ";
				editStr=new StringBuffer("<input type=\"text\" name=\""+prop.getPropertyName()+".id\" value=\"${"+entitysimplName+"."+prop.getPropertyName()+".id}\" ");
				editStr.append( " class=\"easyui-combotree\" ");
				 if(prop.getNullable()!=null&&prop.getNullable()==false){
						data_options+="required:true,";
				 }
				 String content=prop.getEditContent().trim().replace("{", "").replace("}", "");
		    		String[] sdasd=content.split(",");
		    		String url=sdasd[0].replace("url:", "");
		    		String valueField=sdasd[1].replace("valueField:", "");
		    		String textField=sdasd[2].replace("textField:", "");
		    		String options="url:'${ctx}"+url+"',method:'post',valueField:'"+valueField+"',textField:'"+textField+"'";
				 data_options+=options;
				 
				 editStr.append(" data-options=\""+data_options+"\"");
				 editStr.append("/>");
				 queryStr.append(editStr.toString().replace(" data-options=\""+data_options+"\"", " ")
						 .replace("name=\""+prop.getPropertyName()+".id\"", " name=\"webParams["+prop.getPropertyName()+".id]\" ")
						 .replace("value=\"${"+entitysimplName+"."+prop.getPropertyName()+".id}\"", " "));
				 
			}else if("lookup".equals(editType)){}
			if(prop.getIsQueryCondtion()){
				prop.setGeneratedQueryOfPropertyStr(queryStr.toString());
			}
			prop.setGeneratedListDisplayOfPropertyStr(displayStr.toString());
			prop.setGeneratedEditOfPropertyStr(editStr.toString());
		
	
	}
}
