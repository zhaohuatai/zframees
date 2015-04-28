dialog = function(opts) {
	var query = parent.$, fnClose = opts.onClose;
	opts = query.extend({
		title : 'My Dialog',
		width : 400,
		height : 220,
		closed : false,
		cache : false,
		modal : true,
		html : '',
		url : '',
		viewModel : query.noop
	}, opts);

	opts.onClose = function() {
		if (query.isFunction(fnClose)){
			fnClose();
		}
		query(this).dialog('destroy');
	};

	if (query.isFunction(opts.html))
		opts.html = utils.functionComment(opts.html);
	else if (/^\#.*\-template$/.test(opts.html))
		opts.html = $(opts.html).html();

	var win = query('<div></div>').appendTo('body').html(opts.html);
	if (opts.url)
		query.ajax({
			async : false,
			url : opts.url,
			success : function(d) {
				win.empty().html(d);
			}
		});

	win.dialog(opts);
	query.parser.onComplete = function() {
		if ("undefined" === typeof ko)
			opts.viewModel(win);
		else
			ko.applyBindings(new opts.viewModel(win), win[0]);

		query.parser.onComplete = query.noop;
	};
	query.parser.parse(win);
	return win;
};