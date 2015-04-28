var content_error_warn_info_correct=
	'<div style="padding:5px;text-align:left ;">'+
		'<div>'+
			'&nbsp; &nbsp;'+
		    '<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-#icon#"></a>&nbsp;'+
		    '<font style="font-weight: bold;size: 15em;">#alertTitle#：</font>'+
		    '<hr style="width: 95%;color: gray;" size="1px" noshade="true"/>'+
		    '</div>'+
		    '<div style=" width: 99%;height: 76px ;overflow-y:auto;">'+
		    '&nbsp; &nbsp;'+
		    '<font style="size: 13em;" >'+
		    '#alertMessage#'+
		    '</font>'+
	    '</div>'+
    '</div>';
var content_confirm=
	'<div style="padding:5px;text-align:left ;">'+
		'<div>'+
			'&nbsp; &nbsp;'+
		    '<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-#icon#"></a>&nbsp;'+
		    '<font style="font-weight: bold;size: 15em;">#alertTitle#：</font>'+
		    '<hr style="width: 95%;color: gray;" size="1px" noshade="true"/>'+
		    '</div>'+
		    '<div style=" width: 99%;height: 66px ;overflow-y:auto;">'+
		    '&nbsp; &nbsp;'+
		    '<font style="size: 13em;" >'+
		    '#alertMessage#'+
		    '</font>'+
	    '</div>'+
    '</div>';
$.setRegional("alertMsg", {
//	title:{error:"Error", info:"Information", warn:"Warning", correct:"Successful", confirm:"Confirmation"},
	title:{error:"错误提示", info:"信息提示", warn:"警告提示", correct:"成功提示", confirm:"确认提示"},
	icon:{error:"no", info:"alertinfo", warn:"alertwarn", correct:"alertok", confirm:"alertconfirm"},
	butMsg:{ok:"OK", yes:"Yes", no:"No", cancel:"Cancel"}
});

$.alertMessageDialog = function(options,msgshow) {
	//if (window.top.find("#commonEditDialog_div")==false) {// 避免重复弹出
		var opts = $.extend({
			top:0,
			width : 360,
			height : 180,
			modal : true,
			onClose : function() {
				window.top.$("#alertMessageDialog_div").dialog('destroy');
				window.top.$("#alertMessageDialog_div").remove();
			}
		}, options);
		opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
		var winddd=window.top.$("<div id=\"alertMessageDialog_div\" class=\"easyui-dialog\"><div/>");
		if(winddd){
			winddd.empty();
		}
		var targetDialog=window.top.$("<div id=\"alertMessageDialog_div\">"+msgshow+"<div/>").dialog(opts);
		targetDialog.empty();
		targetDialog.append(msgshow);
		$.parser.parse(targetDialog);
		return $.alertMessageDialog.handler =targetDialog;
	//}
};
alertMsg = {
	_closeTimer: null,
	_types: {error:"error", info:"info", warn:"warn", correct:"correct", confirm:"confirm"},
	_getTitle: function(key){
		return $.regional.alertMsg.title[key];
	},
	_getIcon: function(key){
		return $.regional.alertMsg.icon[key];
	},
	_keydownOk: function(event){
		if (event.keyCode == ZHT.keyCode.ENTER) event.data.target.trigger("click");
		return false;
	},
	_keydownEsc: function(event){
		if (event.keyCode == ZHT.keyCode.ESC) event.data.target.trigger("click");
	},

	_open: function(type, msg, buttons){
		window.top.$("#alertMessageDialog_div").dialog('destroy');
		window.top.$("#alertMessageDialog_div").remove();
		if (alertMsg._types.confirm == type){
			var msgshow=content_error_warn_info_correct
						.replace("#alertTitle#", alertMsg._getTitle(type))
						.replace("#icon#", alertMsg._getIcon(type))
						.replace("#alertMessage#", msg);
			 $.alertMessageDialog({
					title : "",
					buttons : [ {
						text : '&nbsp;确  定&nbsp;',
						handler :buttons[0].call
					}, {
						text : '&nbsp;取  消&nbsp;',
						handler : buttons[1].call
					}
					]
				},msgshow);
		} else {
			var msgshow=content_error_warn_info_correct
						.replace("#alertTitle#", alertMsg._getTitle(type))
						.replace("#icon#", alertMsg._getIcon(type))
						.replace("#alertMessage#", msg);
			 $.alertMessageDialog({
					title : "",
					buttons : [ {
						text : '&nbsp;确  定&nbsp;',
						handler : function() {
							$.alertMessageDialog.handler.dialog('destroy');
							$.alertMessageDialog.handler = undefined;
						}
					}]
				},msgshow);
		}	
		if (this._closeTimer) {
			clearTimeout(this._closeTimer);
			this._closeTimer = null;
		}
		if (this._types.info == type || this._types.correct == type){
			this._closeTimer = setTimeout(function(){alertMsg.close();}, 3000);
		} 
	},
	close: function(){
		window.top.$("#alertMessageDialog_div").dialog('close');
		window.top.$("#alertMessageDialog_div").dialog('destroy');
		window.top.$("#alertMessageDialog_div").remove();
		
		//$.alertMessageDialog.handler.dialog('destroy');
		//$.alertMessageDialog.handler = undefined;	
	},
	error: function(msg, options) {
		this._alert(this._types.error, msg, options);
	},
	info: function(msg, options) {
		this._alert(this._types.info, msg, options);
	},
	warn: function(msg, options) {
		this._alert(this._types.warn, msg, options);
	},
	correct: function(msg, options) {
		this._alert(this._types.correct, msg, options);
	},
	_alert: function(type, msg, options) {
		var op = {okName:$.regional.alertMsg.butMsg.ok, okCall:null};
		$.extend(op, options);
		var buttons = [
			{name:op.okName, call: op.okCall, keyCode:ZHT.keyCode.ENTER}
		];
		this._open(type, msg, buttons);
	},
	
	confirm: function(msg, options) {
		var op = {okName:$.regional.alertMsg.butMsg.ok, okCall:null, cancelName:$.regional.alertMsg.butMsg.cancel, cancelCall:null};
		$.extend(op, options);
		var buttons = [
			{name:op.okName, call: op.okCall, keyCode:ZHT.keyCode.ENTER},
			{name:op.cancelName, call: op.cancelCall, keyCode:ZHT.keyCode.ESC}
		];
		this._open(this._types.confirm, msg, buttons);
	}
};

