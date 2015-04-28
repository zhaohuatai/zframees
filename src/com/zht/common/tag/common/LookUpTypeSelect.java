package com.zht.common.tag.common;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.zht.common.codegen.model.LookUpType;

public class LookUpTypeSelect extends SimpleTagSupport{
	private String name;
	private String id;
	private String width;
	private String cssClass;
	private String selectedValue;
	private Boolean hasEmptyChose;
	private String createSelet() {
		String style="";
		int wt=0;
		if(width!=null&&width.length()>0){
			try{
				 wt=Integer.parseInt(width);
			}catch(Exception e){
				wt=100;
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
		LookUpType[] LookUpTypse=LookUpType.values();
		for(LookUpType value:LookUpTypse){  
            strBuffer.append("<option value=\""+value+"\">"+value+"</option>" ).append("\r\n");
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

public static void main(String[] sad){
	LookUpType[] LookUpTypse=LookUpType.values();
	
	for(LookUpType value:LookUpTypse){  
       System.out.println("<option value=\""+value+"\">"+value+"</option>" );
    }  
}

	
	
}
