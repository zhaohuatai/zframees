/**
 * @author zhaohuatai@kgmon.com
 * 
 */

var ZHT = {
		statusCode: {ok:200, error:300, timeout:301,serverError:500},
		
	// sbar: show sidebar
	keyCode: {
		ENTER: 13, ESC: 27, END: 35, HOME: 36,
		SHIFT: 16, TAB: 9,
		LEFT: 37, RIGHT: 39, UP: 38, DOWN: 40,
		DELETE: 46, BACKSPACE:8
	},
	msg:function(key, args){
		var _format = function(str,args) {
			args = args || [];
			var result = str || "";
			for (var i = 0; i < args.length; i++){
				result = result.replace(new RegExp("\\{" + i + "\\}", "g"), args[i]);
			}
			return result;
		};
		return _format(this._msg[key], args);
	},
	ajaxError:function(xhr, ajaxOptions, thrownError){
		if (alertMsg) {
			alertMsg.error("<div>Http status: " + xhr.status + " " + xhr.statusText + "</div>" 
				+ "<div>ajaxOptions: "+ajaxOptions + "</div>"
				+ "<div>thrownError: "+thrownError + "</div>"
				+ "<div>"+xhr.responseText+"</div>");
		} else {
			alert("Http status: " + xhr.status + " " + xhr.statusText + "\najaxOptions: " + ajaxOptions + "\nthrownError:"+thrownError + "\n" +xhr.responseText);
		}
	},

	ajaxDone:function(json){
		if(json.statusCode == ZHT.statusCode.error) {
			if(json.message && alertMsg){
				alertMsg.error(json.message);
			}
		}else if(json.statusCode == ZHT.statusCode.serverError) {
			if(json.message && alertMsg){
				alertMsg.error(json.message);
			}
		} else if (json.statusCode == ZHT.statusCode.timeout) {
			if(alertMsg) {
				alertMsg.error(json.message || ZHT.msg("sessionTimout"), {okCall:ZHT.loadLogin});
			}
			else {
				ZHT.loadLogin();
			}
		} else {
			if(json.message && alertMsg){
				alertMsg.correct(json.message);
			}
		};
	},
	ajaxDoneAndCloseDialog:function(json){
		if(json.statusCode == ZHT.statusCode.error) {
			if(json.message && alertMsg){
				alertMsg.error(json.message);
			}
		} else if(json.statusCode == ZHT.statusCode.serverError) {
			if(json.message && alertMsg){
				alertMsg.error(json.message);
			}
		} else if (json.statusCode == ZHT.statusCode.timeout) {
			if(alertMsg) {
				alertMsg.error(json.message || ZHT.msg("sessionTimout"), {okCall:ZHT.loadLogin});
			}
			else {
				ZHT.loadLogin();
			}
		} else {
			if(json.message && alertMsg){
				alertMsg.correct(json.message);
				editDialog.close(2000);
			}
		};
	},
	ajaxDoneForServerError:function(result){
		if(!result){
			alertMsg.error("未返回任何数据");
		}else if(result.statusCode==300){
			alertMsg.error(result.message);
		}else if(result.statusCode==500){
			alertMsg.error(result.message);
		}else if(result.statusCode==401){
			alertMsg.error(result.message);
		}else{
			//ZHT.loadLogin();
		}
		return result;
	},
	loadLogin:function(){
		alert("loadLogin");
	    var url="${ctx}/login.jsp";
		var options={title:"用户登录",width:'100%',height:'100%', 
				url:url,params:{},onClosed:function(){}};
		editDialog.open(options);
	},
};


(function($){
	$.setRegional = function(key, value){
		if (!$.regional) $.regional = {};
		$.regional[key] = value;
	};
	/**
	 * 扩展String方法
	 */
	$.extend(String.prototype, {
		isPositiveInteger:function(){
			return (new RegExp(/^[1-9]\d*$/).test(this));
		},
		isInteger:function(){
			return (new RegExp(/^\d+$/).test(this));
		},
		isNumber: function(value, element) {
			return (new RegExp(/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/).test(this));
		},
		trim:function(){
			return this.replace(/(^\s*)|(\s*$)|\r|\n/g, "");
		},
		startsWith:function (pattern){
			return this.indexOf(pattern) === 0;
		},
		endsWith:function(pattern) {
			var d = this.length - pattern.length;
			return d >= 0 && this.lastIndexOf(pattern) === d;
		},
		replaceSuffix:function(index){
			return this.replace(/\[[0-9]+\]/,'['+index+']').replace('#index#',index);
		},
		trans:function(){
			return this.replace(/&lt;/g, '<').replace(/&gt;/g,'>').replace(/&quot;/g, '"');
		},
		encodeTXT: function(){
			return (this).replaceAll('&', '&amp;').replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;");
		},
		replaceAll:function(os, ns){
			return this.replace(new RegExp(os,"gm"),ns);
		},
		replaceTm:function($data){
			if (!$data) return this;
			return this.replace(RegExp("({[A-Za-z_]+[A-Za-z0-9_]*})","g"), function($1){
				return $data[$1.replace(/[{}]+/g, "")];
			});
		},
		replaceTmById:function(_box){
			var $parent = _box || $(document);
			return this.replace(RegExp("({[A-Za-z_]+[A-Za-z0-9_]*})","g"), function($1){
				var $input = $parent.find("#"+$1.replace(/[{}]+/g, ""));
				return $input.val() ? $input.val() : $1;
			});
		},
		isFinishedTm:function(){
			return !(new RegExp("{[A-Za-z_]+[A-Za-z0-9_]*}").test(this)); 
		},
		skipChar:function(ch) {
			if (!this || this.length===0) {return '';}
			if (this.charAt(0)===ch) {return this.substring(1).skipChar(ch);}
			return this;
		},
		isValidPwd:function() {
			return (new RegExp(/^([_]|[a-zA-Z0-9]){6,32}$/).test(this)); 
		},
		isValidMail:function(){
			return(new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(this.trim()));
		},
		isSpaces:function() {
			for(var i=0; i<this.length; i+=1) {
				var ch = this.charAt(i);
				if (ch!=' '&& ch!="\n" && ch!="\t" && ch!="\r") {return false;}
			}
			return true;
		},
		isPhone:function() {
			return (new RegExp(/(^([0-9]{3,4}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0-9]{3,4}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/).test(this));
		},
		isUrl:function(){
			return (new RegExp(/^[a-zA-z]+:\/\/([a-zA-Z0-9\-\.]+)([-\w .\/?%&=:]*)$/).test(this));
		},
		isExternalUrl:function(){
			return this.isUrl() && this.indexOf("://"+document.domain) == -1;
		}
	});
})(jQuery);

