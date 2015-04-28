<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/easyui-selfdefine.jsp" %>
<script src="${pageContext.request.contextPath}/resources/easyuilayout/north_nav.jsp.js" type="text/javascript"></script>
 
<div id="navLineDiv" style="position: absolute;  bottom: 2px;display: inline; width: 100%">
<div class="easyui-tabs" id="modules_nav_tabs" data-options="tabWidth:100,tools:'#tab-tools',border:false, plain:true," style="width:100%;height:30px ; padding-right:130px; float:left;  left: 0px; bottom: 0px;" ></div>
<div style="position: absolute; right: 0px; bottom: 0px; width:130px; float: left;">
	<a href="javascript:void(0)" class="easyui-menubutton" data-options="plain:false"  menu="#layout_north_mkqhMenu_R4LxQY6CR4CFEnZso71mrg" iconCls="icon-help" >控制面板</a>
	<a id="btnHideNorth" onclick="ZHTEASYUtil.hiddenNorth();" class="easyui-linkbutton"  data-options="plain: true, iconCls: 'layout-button-up'"></a>
</div>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div onclick="showUserInfo();">个人信息</div>
	<div class="menu-sep"></div>
	<div>
		<span>系统注销</span>
		<div style="width: 120px;">
			<div onclick="loginAndRegDialog.dialog('open');">锁定窗口</div>
			<div class="menu-sep"></div>
			<div onclick="logout();">重新登录</div>
			<div onclick="logout(true);">退出系统</div>
		</div>
	</div>
	<div class="menu-sep"></div>
	<div>
		<span>更换主题</span>
		<div style="width: 120px;">
			<div onclick="ZHTEASYUtil.changeTheme('default');">default</div>
			<div onclick="ZHTEASYUtil.changeTheme('gray');">gray</div>
			<div onclick="ZHTEASYUtil.changeTheme('black');">black</div>
			<div onclick="ZHTEASYUtil.changeTheme('bootstrap');">bootstrap</div>
			<div onclick="ZHTEASYUtil.changeTheme('metro');">metro</div>
			
			<div onclick="ZHTEASYUtil.changeTheme('metro-blue');">metro-blue</div>
			<div onclick="ZHTEASYUtil.changeTheme('metro-gray');">metro-gray</div>
			<div onclick="ZHTEASYUtil.changeTheme('metro-green');">metro-green</div>
			<div onclick="ZHTEASYUtil.changeTheme('metro-orange');">metro-orange</div>
			<div onclick="ZHTEASYUtil.changeTheme('metro-red');">metro-red</div>
			<div onclick="ZHTEASYUtil.changeTheme('ui-cupertino');">ui-cupertino</div>
			<div onclick="ZHTEASYUtil.changeTheme('ui-dark-hive');">ui-dark-hive</div>
			<div onclick="ZHTEASYUtil.changeTheme('ui-pepper-grinder');">ui-pepper-grinder</div>
			<div onclick="ZHTEASYUtil.changeTheme('ui-sunny');">ui-sunny</div>
		</div>
	</div>
</div>
<div id="layout_north_mkqhMenu_R4LxQY6CR4CFEnZso71mrg" style="width: 100px; display: none;">
	<div onclick="loginAndRegDialog.dialog('open');">锁定窗口</div>
	<div class="menu-sep"></div>
	<div onclick="logout();">重新登录</div>
	<div onclick="logout(true);">退出系统</div>
</div>
	
