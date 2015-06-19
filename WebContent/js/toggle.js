(function(_$) {
	var control = false;
	$.fn.toogleText = function(obj) {
		var o = obj;
		if (control == false) {
			control = true;
			eval("this." + o.type + "('" + obj.text[0] + "');");
			$.post("ctrl?acao=ligaBomba", function(retorno) {
				
			});
		} else {
			control = false;
			eval("this." + o.type + "('" + obj.text[1] + "');");	
			$.post("ctrl?acao=desligaBomba", function(retorno) {
				
			});
		}
	}
})(jQuery);