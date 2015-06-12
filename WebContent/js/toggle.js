(function(_$) {
	var control = false;
	$.fn.toogleText = function(obj) {
		var o = obj;
		if (control == false) {
			control = true;
			eval("this." + o.type + "('" + obj.text[0] + "');");
			$.ajax({
				url : "192.168.1.11",
				data : {'acao' : '001'},
				type : 'GET',
				timeout : 3000,
				success : function(retorno) {
					
				},
				error : function(erro) {
					
				}
			})
		} else {
			control = false;
			eval("this." + o.type + "('" + obj.text[1] + "');");
			$.ajax({
				url : "192.168.1.11",
				data : {'acao' : '002'},
				type : 'GET',
				timeout : 3000,
				success : function(retorno) {
					
				},
				error : function(erro) {
					
				}
			})
		}
	}
})(jQuery);