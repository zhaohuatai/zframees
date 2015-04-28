<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<script type="text/javascript" charset="utf-8">
	function logout(b) {
		/*$.post('systemAction!logout.action', function() {
			if (b) {
				if (jqueryUtil.isLessThanIe8()) {
					loginAndRegDialog.dialog('open');
				} else {
						location.replace('login.jsp');
				}
			} else {
				loginAndRegDialog.dialog('open');
			}
		});*/
		$.messager.confirm("提示", "确认退出吗?",function(r){
			if(r){
				$.ajax({
					async : false,
					cache : false,
					type : "POST",
					url : "systemAction!logout.action",
					error : function() {
					},
					success : function(json) {
						location.replace("login.jsp");
					}
				});
			}
		});
		
	}

	var userInfoWindow;
	function showUserInfo() {
		userInfoWindow = $('<div/>').window({
			modal : true,
			title : '当前用户信息',
			width : 350,
			height : 300,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			//href : 'userAction!showUserInfo.action',
			onClose : function() {
				$(this).window('destroy');
			}
		});
	}
	$(function(){
		
		});

	

</script>
<div id="navLineDiv" style="position: absolute;  bottom: 0px;display: inline; width: 100%">
<div style="position: absolute; right: 0px; bottom: 0px; width:100px; float: left;">
	<a href="javascript:void(0)" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help" >控制面板</a>
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
			<div onclick="changeTheme('default');">default</div>
			<div onclick="changeTheme('gray');">gray</div>
			<div onclick="changeTheme('black');">black</div>
			<div onclick="changeTheme('bootstrap');">bootstrap</div>
			<div onclick="changeTheme('metro');">metro</div>
		</div>
	</div>
</div>
<div id="layout_north_mkqhMenu" style="width: 100px; display: none;">
	<div onclick="loginAndRegDialog.dialog('open');">锁定窗口</div>
	<div class="menu-sep"></div>
	<div onclick="logout();">重新登录</div>
	<div onclick="logout(true);">退出系统</div>
</div>
