<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script src="${pageContext.request.contextPath}/resources/easyuilayout/center.jsp.js" type="text/javascript"></script>
<div id="centerTabs" style="height: 30px; padding: 0px;overflow: hidden;" data-options="tools:'#centerTabs-tab-tools'">
	<div iconCls="icon-home" title="首页" border="false" style="overflow: hidden;">
		<%-- 
		<iframe src="resources/easyuilayout/home.jsp" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>
	--%>
	</div>
</div>
<div id="centerTabs-tab-tools">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick="ZHTEASYUtil.reloadSelectedTab('centerTabs')"></a>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="ZHTEASYUtil.closeSelectedTab('centerTabs')"></a>
</div>
<div id="tabsMenu" style="width: 120px;display:none;">
	<div type="refresh">刷新</div>
	<div class="menu-sep"></div>
	<div type="close">关闭</div>
	<div type="closeOther">关闭其他</div>
	<div type="closeAll">关闭所有</div>
</div>
