package com.zht.common.tag.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.zht.framework.util.ZStrUtil;

public class AuthLinkButton extends SimpleTagSupport{
	private String id;
	private String iconCls;
	private String onclick;
	private String plain;
	private String text;
	private String authStr;
	private String roleCode;
	private String permitCode;
	
	
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
			String treeStr=this.createLinkButton();
			if(treeStr!=null&&!"".equals(treeStr)){
				 getJspContext().getOut().write(treeStr.toString());
			}
		}
		private String createLinkButton() {
			Subject subject=SecurityUtils.getSubject();
			//subject.hasRole(""+roleCode);
			boolean hasPermission=true;
			
			StringBuffer strBuffer=new StringBuffer("");
			strBuffer.append("<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\"  ");
			if(id!=null&&!"".equals(id)){
				strBuffer.append(" id=\""+id+"\" ");
			}
			if(plain!=null&&"true".equals(plain)){
				strBuffer.append(" plain=\"true\" ");
			}
			if(iconCls!=null&&!"".equals(iconCls)){
				strBuffer.append(" iconCls=\""+iconCls+"\" ");
			}
			if(onclick!=null&&!"".equals(onclick)){
				strBuffer.append(" onclick=\""+onclick+"\" ");
			}
			
			if(!ZStrUtil.isEmptyAfterTrimE(permitCode)){
				if(!subject.isPermitted(""+permitCode)){
					hasPermission=false;
				}
			}
			
			//拥有角色即可---此处逻辑有错，去掉该处
//			if(!ZStrUtil.isEmpty(roleCode)){
//				if(!subject.hasRole(""+roleCode)){
//					hasPermission=false;
//				}
//			}
			
			if(!hasPermission){
				strBuffer.append("  data-options=\"disabled:true\" ");
				strBuffer.append(">");
				strBuffer.append("<font color=\"red\">["+text+"]</font>");
			}else{
				strBuffer.append(">");
				strBuffer.append(text);
			}
			strBuffer.append("</a>");
			return strBuffer.toString();
		}

		public String getIconCls() {
			return iconCls;
		}

		public void setIconCls(String iconCls) {
			this.iconCls = iconCls;
		}

		public String getOnclick() {
			return onclick;
		}

		public void setOnclick(String onclick) {
			this.onclick = onclick;
		}

		public String getPlain() {
			return plain;
		}

		public void setPlain(String plain) {
			this.plain = plain;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getAuthStr() {
			return authStr;
		}

		public void setAuthStr(String authStr) {
			this.authStr = authStr;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getRoleCode() {
			return roleCode;
		}
		public void setRoleCode(String roleCode) {
			this.roleCode = roleCode;
		}
		public String getPermitCode() {
			return permitCode;
		}
		public void setPermitCode(String permitCode) {
			this.permitCode = permitCode;
		}
		
		
}
