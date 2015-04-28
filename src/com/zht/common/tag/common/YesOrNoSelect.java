package com.zht.common.tag.common;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class YesOrNoSelect extends SimpleTagSupport{
	private String name;
	private String id;
	private String width;
	private String yesVlaue;
	private String noValue;
	private String cssClass;
	private String selectedValue;
	private Boolean hasEmptyChose;
	private String createSelet() {
		if(yesVlaue==null||"".equals(yesVlaue)){
			yesVlaue="1";
		}
		if(noValue==null||"".equals(noValue)){
			noValue="0";
		}
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
		if(yesVlaue.equals(selectedValue)){
			strBuffer.append("<option value=\""+yesVlaue+"\" selected=\"selected\">是</option>" ).append("\r\n");
		}else{
			strBuffer.append("<option value=\""+yesVlaue+"\">是</option>" ).append("\r\n");
		}
		if(noValue.equals(selectedValue)){
			strBuffer.append("<option value=\""+noValue+"\"  selected=\"selected\">否</option>" ).append("\r\n");
		}else{
			strBuffer.append("<option value=\""+noValue+"\">否</option>" ).append("\r\n");
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

	public String getYesVlaue() {
		return yesVlaue;
	}

	public void setYesVlaue(String yesVlaue) {
		this.yesVlaue = yesVlaue;
	}

	public String getNoValue() {
		return noValue;
	}

	public void setNoValue(String noValue) {
		this.noValue = noValue;
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
