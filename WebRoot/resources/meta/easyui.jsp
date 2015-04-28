<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<%
		String easyuiThemeName="default";
		Cookie cookies[] =request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie : cookies){
				if (cookie.getName().equals("easyuiThemeName")) {
					easyuiThemeName = cookie.getValue();
					break;
				}
			}
		}
	%>
<link id="easyuiTheme"  href="${pageContext.request.contextPath}/resources/easyui141/themes/<%=easyuiThemeName %>/easyui.css" rel="stylesheet" type="text/css"></link>
<link href="${pageContext.request.contextPath}/resources/easyui141/themes/icon.css" rel="stylesheet" type="text/css"></link>

<script src="${pageContext.request.contextPath}/resources/easyui141/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/easyui141/easyloader.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/easyui141/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
