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
        <div id="toolbar" class="panel-header top-toolbar" style="position: absolute;  bottom: 0px;display: inline; height:20px;width: 100%">
            <a id="btn2" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-bug'">按钮2</a>
            <a id="btn2" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-bug'">按钮2</a>
            <a id="btn2" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-bug'">按钮2</a>
            <a id="btn2" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-bug'">按钮2</a>
            <a id="btn2" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-bug'">按钮2</a>
            <a id="btn2" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-bug'">按钮2</a>
            <a id="btn2" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-bug'">按钮2</a>
            </div>	
