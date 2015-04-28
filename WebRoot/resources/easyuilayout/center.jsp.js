	var centerTabs;
	var tabsMenu;
	$(function() {
		tabsMenu = $('#tabsMenu').menu({
			onClick : function(item) {
				var curTabTitle = $(this).data('tabTitle');
				var type = $(item.target).attr('type');

				if (type === 'refresh') {
					refreshTab(curTabTitle);
					return;
				}

				if (type === 'close') {
					var t = centerTabs.tabs('getTab', curTabTitle);
					if (t.panel('options').closable) {
						centerTabs.tabs('close', curTabTitle);
					}
					return;
				}

				var allTabs = centerTabs.tabs('tabs');
				var closeTabsTitle = [];

				$.each(allTabs, function() {
					var opt = $(this).panel('options');
					if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable && type === 'closeAll') {
						closeTabsTitle.push(opt.title);
					}
				});

				for ( var i = 0; i < closeTabsTitle.length; i++) {
					centerTabs.tabs('close', closeTabsTitle[i]);
				}
			}
		});

		centerTabs = $('#centerTabs').tabs({
						
			fit : true,
			border : false,
			onContextMenu : function(e, title) {
				e.preventDefault();
				tabsMenu.menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data('tabTitle', title);
			}
		});
		
	});
	function addTab(nodes) {
		var node=nodes.split("||");
		var tabTitle=node[0];
		var iconCls=node[1];
		var tabUrl=node[2];
		if (centerTabs.tabs('exists',tabTitle)) {
			centerTabs.tabs('select', tabTitle);
		} else {
				centerTabs.tabs('add', {
					title : tabTitle,
					closable : true,
					//border:false,
					iconCls : iconCls,
					//href : 'error/error.jsp',
					content : "<iframe src="+tabUrl+" frameborder=\"0\" style=\"border:0;width:100%;height:99.7%;overflow: hidden;\"></iframe>",
					tools : [ {
						iconCls : 'icon-mini-refresh',
						handler : function() {
							refreshTab(tabTitle);
						}
					} ]
				});
			
		}
	}
	function refreshTab(title) {
		var tab = centerTabs.tabs('getTab', title);
		centerTabs.tabs('update', {
			tab : tab,
			options : tab.panel('options')
		});
	}
	
