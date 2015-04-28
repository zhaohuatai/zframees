	var isModuleNavFirstLod=0;
	var moduleNavDiv=$("#modules_nav_tabs");
	var ajaModuleUrl="rbac/rbacModule/loadModules";
	var ajaMenuUrl="rbac/menu/loadMenus";
$(function(){
	loadAllModulNavTabs(ajaModuleUrl,ajaMenuUrl);
	$("#modules_nav_tabs").tabs({
		onSelect: function(title){
			if(isModuleNavFirstLod==1){
				 var tab = $("#modules_nav_tabs").tabs("getSelected"); 
				 var targetId=ZHTEASYUtil._decodeTabId(tab.panel('options').id);
				 loadMenusByModuleId(targetId,ajaMenuUrl);
			}
		}
	});
})	;
function logout(b) {
	alertMsg.confirm("确认退出吗?", {
		cancelCall : function() {alertMsg.close();},
		okCall : function() {alertMsg.close();
				var ajaxUrl = "rbac/user/loginout";
				ZHTAJAX.ajaxTodo(ajaxUrl, {}, function(data) {
					location.replace("sessiontimeout.jsp");
				});
			}
		});
}
	function loadAllModulNavTabs(ajaxModuleUrl,ajaxMenuUrl){
		var tabsDivId=$("#modules_nav_tabs");
		var firstTabId=null;
		var disIndex=99999999999999999999;
		ZHTAJAX.ajaxTodo(ajaxModuleUrl,{},function(data){
			var count=data.length;
			$.each(data, function(i, item) {
				var display=item.display;
				var tabId =item.id;
				var index =item.disIndex;
				if(index<=disIndex){
					disIndex=index;
					firstTabId=tabId;
				}
				tabsDivId.tabs('add',{ 
					     title:display,   
					     content:"",  
					     iconCls:'icon-color',
					     id:ZHTEASYUtil._encodeTabId(tabId)
					  });  
				if(i>=count-1){
					isModuleNavFirstLod=1;
				}
			});
			tabsDivId.tabs("selectById",ZHTEASYUtil._encodeTabId(firstTabId));
			var targetTab=tabsDivId.tabs("getTabById",ZHTEASYUtil._encodeTabId(firstTabId));
			//var targetId=ZHTEASYUtil._decodeTabId(targetTab.panel('options').id);
			//loadMenusByModuleId(targetId,ajaxMenuUrl);
		 });
	}

	function loadMenusByModuleId(moduleId,ajaxMenuUrl){
		ZHTAJAX.ajaxTodo(ajaxMenuUrl,{"moduleId":moduleId,useModule:true},function(data){
			 var $ma=$("#menuAccordion");
			 $ma.find(">div").each(function(){
				 this.remove();
			 });
			 $ma.accordion({animate:true,fit:true,border:false,collapsible: true,});
				 $.each(data,function(i, item){
					 var menulist ="<div style=\"padding:2px 2px;\" >";
					 if(item.linkbuttonViewList &&item.linkbuttonViewList.length>0){
						 $.each(item.linkbuttonViewList,function(ci,citem){
							 var menuTitle=citem.display;
								var effort=citem.display+"||"+citem.iconCls+"||"+citem.url;
								menulist+=""+ 
								"<a href=\"javascript:void(0);\" "+
								"style=\"width:100%; \" "+
								"class=\"easyui-linkbutton\" "+
								"data-options=\"plain:false,iconCls:'"+'icon-color'+"' \" "+
								"onclick=\"addTab('"+effort+"');\">"+menuTitle+
								"</a><br/>";
							});
					 }
					 menulist+="</div>";
					$ma.accordion("add", {
				           title: item.title,
				           content: menulist,
						   border:false,
				           iconCls: item.iconCls,
				           selected: false
				    });
				 });
				 $ma.accordion("select",0);
		 }); 
		
	}
	

	function buquan(text){
		if(text.length>=10){
			return text;
		}
		for(var j=text.length;j<10;j++){
			text+="　&nbsp;";
		}
		return text;
	}
