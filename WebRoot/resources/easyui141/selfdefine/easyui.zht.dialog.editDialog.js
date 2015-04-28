
$.commonEditDialog = function(options) {
	//if (window.top.find("#commonEditDialog_div")==false) {// 避免重复弹出
		var opts = $.extend({
			width : 840,
			height : 600,
            maximizable: true,
			modal : true,
		}, options);
		opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
		var winddd=window.top.$("<div id=\"commonEditDialog_div\" class=\"easyui-dialog\"><div/>");
		if(winddd){
			winddd.empty();
		}
		var targetDialog=window.top.$("<div id=\"commonEditDialog_div\" class=\"easyui-dialog\"><div/>").dialog(opts);
			$.parser.parse(targetDialog);
		return $.commonEditDialog.handler =targetDialog;
	//}
};


editDialog = {
	close: function(timeout){
		if(timeout){
			setTimeout(function(){
				window.top.$("#commonEditDialog_div").dialog('close');
				window.top.$("#commonEditDialog_div").dialog('destroy');
				window.top.$("#commonEditDialog_div").remove();
			}, timeout);
		}else{
			window.top.$("#commonEditDialog_div").dialog('close');
			window.top.$("#commonEditDialog_div").dialog('destroy');
			window.top.$("#commonEditDialog_div").remove();
		}

		
	},
	open: function(options) {
		window.top.$("#commonEditDialog_div").dialog('destroy');
		window.top.$("#commonEditDialog_div").remove();
		var title= options.title;
		var height= options.height;
		var width= options.width;
		var paramsStr=this.getParams(options.params);
		var url= options.url;
		if(paramsStr&&paramsStr.toString().length>0){
			url+="?"+paramsStr;
		}
		 $.commonEditDialog({
				title : title,
				height:height,
				width:width,
				href : url,
				method:"post",
				onClose :function(){
					window.top.$("#commonEditDialog_div").dialog('destroy');
					window.top.$("#commonEditDialog_div").remove();
					options.onClosed();
				},
			});
	},
	getParams:function(params){
		if(!params||params==undefined){
			return "";
		}
		var _str = ""; 
		 for(var obj in params){ 
			 _str += obj + "=" + params[obj] + "&";
		 }
		 _str = _str.substring(0, _str.length-1); 
		 return _str;
	}
};

