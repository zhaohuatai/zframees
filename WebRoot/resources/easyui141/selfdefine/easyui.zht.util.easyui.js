
var ZHTEASYUtil ={
		closeSelectedTab:function (tabPanelId){
			var index = $('#'+tabPanelId+'').tabs('getTabIndex', $('#centerTabs').tabs('getSelected'));
			var tab = $('#'+tabPanelId+'').tabs('getTab', index);
			if(!tab){
				alertMsg.error("未找到该TAB");
				return ;
			}
			if (tab.panel('options').closable) {
				$('#'+tabPanelId+'').tabs('close', index);
			} else {
				alertMsg.error('[' + tab.panel('options').title + ']  不可以被关闭');
			}
		},
		reloadSelectedTab:function (tabPanelId){
			var index = $('#'+tabPanelId+'').tabs('getTabIndex', $('#centerTabs').tabs('getSelected'));
			var tab = $('#'+tabPanelId+'').tabs('getTab', index);
			if(!tab){
				alertMsg.error("未找到该TAB");
				return ;
			}
			var href = tab.panel('options').href;
			if (href) {/*说明tab是以href方式引入的目标页面*/
				$('#'+tabPanelId+'').tabs('getTab', index).panel('refresh');
			} else {   /*说明tab是以content方式引入的目标页面*/
				var panel = $('#'+tabPanelId+'').tabs('getSelected').panel('panel');
				var frame = panel.find('iframe');
				try {
					if (frame.length > 0) {
						for ( var i = 0; i < frame.length; i++) {
							frame[i].contentWindow.document.write('');
							frame[i].contentWindow.close();
							frame[i].src = frame[i].src;
						}
						//if ($.browser.msie) {
						if(/msie/.test(navigator.userAgent.toLowerCase())){//IE
							CollectGarbage();
						}
					}
				} catch (e) {
				}
			}
		},
		refreshTab:function (title) {
			var tab = centerTabs.tabs('getTab', title);
			centerTabs.tabs('update', {
				tab : tab,
				options : tab.panel('options')
			});
		},
		_encodeTabId:function (tabId){
			return "XeGBFVyg6q4Scbrufo6CuC"+"#"+tabId;
		},
		_decodeTabId:function (tabId){
			if(!tabId){
				return null;
			}
			var decodedId=tabId.substring(tabId.indexOf("#")+1);
			if(decodedId){
				return decodedId;
			}
			return null;
		},
		changeTheme:function (themeName) {/* 更换主题 */
			var $easyuiTheme = $('#easyuiTheme');
			var url = $easyuiTheme.attr('href');
			var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
			$easyuiTheme.attr('href', href);

			var $iframe = $('iframe');
			if ($iframe.length > 0) {
				for ( var i = 0; i < $iframe.length; i++) {
					var ifr = $iframe[i];
					$(ifr).contents().find('#easyuiTheme').attr('href', href);
				}
			}

			$.cookie('easyuiThemeName', themeName, {
				expires : 7,
				path: "/"
			});
		},
		hiddenNorth:function (){
			 $('#main_page_body_layout_PKjC58slRXqte7eALgbjjA').layout('collapse','north'); 
		},
		genQueryParams:function (queryParams,formDataArray){
			$.each(formDataArray, function (index) {
		    	var name=this['name'];
		    	var value=this['value'];
		    	queryParams[""+name]=value;
		    });
		},
		selectRowsToArray:function (rows){
			var idArray = [];
			$.each(rows, function(index, item){
				idArray.push(item.id);
			}); 
			return idArray;
		}
};
