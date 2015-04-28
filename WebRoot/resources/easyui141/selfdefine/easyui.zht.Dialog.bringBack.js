//在dialog中回调  returnBackDialog.close(100,returnValue);
//在openner 	
 //var options={title:"角色添加",width:600,height:400, url:url,onClosed:function(data){
		//console.log("final: "+data);}};
	//returnBackDialog.open(options);
$.returnBackWindow = function(options) {
	//if (window.top.find("#commonEditDialog_div")==false) {// 避免重复弹出
		var opts = $.extend({
			width : 840,
			height : 600,
            maximizable: true,
			modal : true,
			url:'',
		}, options);
		opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
		
		var winddd=window.top.$("<div id=\"returnBackDialog_div\" class=\"easyui-dialog\"><div/>");
		if(winddd){
			winddd.empty();
		}
		var content = '<iframe scrolling="yes" frameborder="0" style="width:100%;height:100%;" src="'+ opts.ifhref +'"></iframe>';
		var targetDialog=window.top.$("<div id=\"returnBackDialog_div\" class=\"easyui-dialog\"><div/>");
			targetDialog.append(content);
			targetDialog.dialog(opts);
			$.parser.parse(targetDialog);
		return $.commonEditDialog.handler =targetDialog;
	//}
};


returnBackDialog = {
	close: function(timeout,returnValue){
		var divsss="<div id=\"div_lookupbackvarValue\"><input type=\"hidden\" value=\""+returnValue+"\" id=\"bringbacklookupbackvarValue\"/></div>";
		$(window.top.document.body).append(divsss); 
		if(timeout){
			setTimeout(function(){
				window.top.$("#returnBackDialog_div").dialog('close');
				window.top.$("#returnBackDialog_div").dialog('destroy');
				window.top.$("#returnBackDialog_div").remove();
			}, timeout);
		}else{
				window.top.$("#returnBackDialog_div").dialog('close');
				window.top.$("#returnBackDialog_div").dialog('destroy');
				window.top.$("#returnBackDialog_div").remove();
		}
		
	},
	open: function(options) {
		window.top.$("#returnBackDialog_div").dialog('destroy');
		window.top.$("#returnBackDialog_div").remove();
		window.top.$("#div_lookupbackvarValue").remove();
		var title= options.title;
		var height= options.height;
		var width= options.width;
		var paramsStr=this.getParams(options.params);
		var url= options.url;
		if(paramsStr&&paramsStr.toString().length>0){
			url+="?"+paramsStr;
		}
		 $.returnBackWindow({
				title : title,
				height:height,
				width:width,
				href : '',
				ifhref : url,
				method:"post",
				onClose :function(){
					var returndata=window.top.$("#div_lookupbackvarValue").find('input[id="bringbacklookupbackvarValue"]').val();
					window.top.$("#returnBackDialog_div").dialog('destroy');
					window.top.$("#returnBackDialog_div").remove();
					
					window.top.$("#div_lookupbackvarValue").remove();
					options.onClosed(returndata);
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
	},
};

