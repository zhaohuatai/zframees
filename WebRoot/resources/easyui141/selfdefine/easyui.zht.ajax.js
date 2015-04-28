var ZHTAJAX={
		validateFromCallback:function (form, callback ) {
		var $form = $(form);
		if (!$form.form('validate')) {
			return false;
		}
		var _submitFn = function(){
			//loadMask.open();
			$.ajax({
				type: form.method || 'POST',
				url:$form.attr("action"),
				data:$form.serializeArray(),
				dataType:"json",
				cache: false,
				success: callback || ZHT.ajaxDone,
				error: ZHT.ajaxError,
				complete:function(){loadMask.close();}
			});
		};
		_submitFn();
		return true;
	},
	ajaxTodo : function(url, param, callback) {
		//loadMask.open();
		$.ajax({
			type : 'POST',
			url : url,
			data : $.param(param, true),
			dataType : "json",
			cache : false,
			success : callback,
			error : ZHT.ajaxError,
			complete : function() {
				loadMask.close();
			}
		});
	}
};

