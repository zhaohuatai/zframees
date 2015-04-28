package com.zht.common.tag.common;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ValidationTypeSelect extends SimpleTagSupport{
	private String name;
	private String id;
	private String width;
	private String cssClass;
	private String selectedValue;
	private Boolean hasEmptyChose;
	private static String[] queryTpes=new String[]{"text","email","phone","password","digits","number","creditcard","alphanumeric","lettersonly","url"};
	private static String[] queryTpeName=new String[]{"普通文本","邮箱","电话","密码","整数","浮点数","信用卡","字母、数字、下划线","字母","网址"};
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
		if(id==null||"".equals(id)){
			if(cssClass==null||"".equals(cssClass)){
				strBuffer.append("<select name=\""+name+"\" "+style+">").append("\r\n");
			}else{
				strBuffer.append("<select name=\""+name+"\" calss=\""+cssClass+"\" "+style+">").append("\r\n");	
			}
		}else{
			if(cssClass==null||"".equals(cssClass)){
				strBuffer.append("<select name=\""+name+"\" id=\""+id+"\" "+style+">").append("\r\n");
			}else{
				strBuffer.append("<select name=\""+name+"\" id=\""+id+"\" calss=\""+cssClass+"\" "+style+">").append("\r\n");	
			}
		}
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
	
	

	
	
}
