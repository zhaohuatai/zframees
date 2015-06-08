
var ZHTPUBUtil ={
		
		browserVsersion:function (){
			if(/msie/.test(navigator.userAgent.toLowerCase())){
				return "msie";
			}else if(/opera/.test(navigator.userAgent.toLowerCase())){
				return "OPERA";
			}else if(/webkit/.test(navigator.userAgent.toLowerCase())){
				return "webkit";
			}else if(/mozilla/.test(navigator.userAgent.toLowerCase())){
				return "mozilla";
			}else {
				return "unknown";
			}
		},
		isLessThanIe8:function (){
			if(/msie/.test(navigator.userAgent.toLowerCase())){//IE
				var b_version=navigator.appVersion;
				var version=b_version.split(";");
				var trim_Version=version[1].replace(/[ ]/g,"");
				if(trim_Version=="MSIE6.0"||trim_Version=="MSIE7.0"||trim_Version=="MSIE8.0"){
					return true;
				}
			}
			return false;
		},
		parse$Json:function (data){
			if(data){
				var result = $.parseJSON(data);
				return result;
			}
			return null;
		},
		parseJson:function (data){
			try{
				if ($.type(data) == 'string'){
					return eval('(' + data + ')');	
				}
				else{
					return data;
				}
			} catch (e){
				return {};
			}
		},
		form2Json:function (formArrayData) {
		    //var arr = $("#" + id).serializeArray();
			var arr=formArrayData;
			if(arr.length<1){
				return JSON.parse("{}");
			}
		    var jsonStr = "";
		    jsonStr += '{';
		    for (var i = 0; i < arr.length; i++) {
		        jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",';
		    }
		    jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
		    jsonStr += '}';
		    var json = JSON.parse(jsonStr);
		    return json;
		},
		form2Jsonssss:function (formId) {
		    var arr = $("#" + id).serializeArray();
		    var jsonStr = "";
		    if(arr.length<1){
				return JSON.parse(jsonStr);
			}
		    jsonStr += '{';
		    for (var i = 0; i < arr.length; i++) {
		        jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",';
		    }
		    jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
		    jsonStr += '}';
		    var json = JSON.parse(jsonStr);
		    return json;
		},
		/*
		 * json to string
		 */
		obj2str:function(o) {
			//var r = [];
			var r = new Array();
			if(typeof o =="string") return "\""+o.replace(/([\'\"\\])/g,"\\$1").replace(/(\n)/g,"\\n").replace(/(\r)/g,"\\r").replace(/(\t)/g,"\\t")+"\"";
			if(typeof o == "object"){
				if(!o.sort){
					for(var i in o)
						r.push(i+":"+ZHT.obj2str(o[i]));
					if(!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)){
						r.push("toString:"+o.toString.toString());
					}
					r="{"+r.join()+"}";
				}else{
					for(var i =0;i<o.length;i++) {
						r.push(ZHT.obj2str(o[i]));
					}
					r="["+r.join()+"]";
				}
				return r;
			}
			return o.toString();
		},
		jsonEval:function(data) {
			try{
				if ($.type(data) == 'string'){
					return eval('(' + data + ')');	
				}
				else{
					return data;
				}
			} catch (e){
				return {};
			}
		},
		formatDate:function(value){
			if(value&&value.length>=10){
				return value.substr(0,10);
			}
			
			//alert("value "+value);
			
			// var dd=new Date("1998","11","29","10","11","44");
			if(value){
				var date = new Date(value);
				if(date){
					alert("date "+date);
				}
				var month = date.getMonth() + 1;
				var day = date.getDate();
				//alert(date+" "+month+" "+ day);
				if(month<10){
					month = '0'+ month;
				}
				if(day<10){
					day = '0'+ day;
				}
				return date.getFullYear() + '-' + month + '-'+ day;
			}
			return '';
		}
};
