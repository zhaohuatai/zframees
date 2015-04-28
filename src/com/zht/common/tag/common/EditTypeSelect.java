package com.zht.common.tag.common;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class EditTypeSelect extends SimpleTagSupport{
	private String name;
	private String id;
	private String width;
	private String cssClass;
	private String selectedValue;
	private Boolean hasEmptyChose;
	private String onchange;
	private static String[] queryTpes=new String[]{  "text","password","textarea","combox","checkbox","radio","file","lookup","date","datetime"};
	private static String[] queryTpeName=new String[]{"文本", "密码",       "长文本",   "下拉框",   "多选框",    "单选框", "文件" , "查找带回","日期",  "日期时间"};
	private static Map<String,String> contenmap=new LinkedHashMap<String,String>();
	
	private String createSelet() {
		
		for(int i=0;i<queryTpes.length;i++){
			contenmap.put(queryTpes[i], queryTpeName[i]);
		}
		String style="";
		int wt=0;
		if(width!=null&&width.length()>0){
			try{
				 wt=Integer.parseInt(width);
			}catch(Exception e){
				//wt=100;
			}
			if(wt>0){
				style="style='width:"+width+"px;'";			
			}
			
		}
		StringBuffer strBuffer=new StringBuffer("");
		String idStr="";
		String cssClassStr="";
		String onchangeStr="";
		if(id!=null||!"".equals(id)){
			idStr=" id=\""+id+"\" ";
		}
		if(cssClass!=null||!"".equals(cssClass)){
			cssClassStr=" calss=\""+cssClass+"\" " ;
		}
		if(onchange!=null||!"".equals(onchange)){
			onchangeStr=" onchange=\""+onchange+"\" " ;
		}
		strBuffer.append("<select name=\""+name+"\" ");
		strBuffer.append(idStr);
		strBuffer.append(cssClassStr);
		strBuffer.append(onchangeStr);
		strBuffer.append(" "+style+">");
		strBuffer.append("\r\n");
	
		if(hasEmptyChose!=null&&hasEmptyChose){
			strBuffer.append("<option value=\"\">请选择</option>" ).append("\r\n");
		}
		
		for(Map.Entry<String, String> entry:contenmap.entrySet()){  
            String key = entry.getKey();  
            String value = entry.getValue();  
            strBuffer.append("<option value=\""+key+"\">"+value+"</option>" ).append("\r\n");
        }  
		
		strBuffer.append("</select>").append("\r\n");
		return strBuffer.toString();
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		String select=this.createSelet();
		if(select!=null&&!"".equals(select)){
			 getJspContext().getOut().write(select.toString());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}


	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	public Boolean getHasEmptyChose() {
		return hasEmptyChose;
	}

	public void setHasEmptyChose(Boolean hasEmptyChose) {
		this.hasEmptyChose = hasEmptyChose;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public static String[] getQueryTpes() {
		return queryTpes;
	}

	public static void setQueryTpes(String[] queryTpes) {
		EditTypeSelect.queryTpes = queryTpes;
	}

	public static String[] getQueryTpeName() {
		return queryTpeName;
	}

	public static void setQueryTpeName(String[] queryTpeName) {
		EditTypeSelect.queryTpeName = queryTpeName;
	}

	public static Map<String, String> getContenmap() {
		return contenmap;
	}

	public static void setContenmap(Map<String, String> contenmap) {
		EditTypeSelect.contenmap = contenmap;
	}
	
	

	
	
}
