(function(_$){ 
	var control = false; 
	$.fn.toogleText = function(obj){ 
		var o = obj; 
		if(control == false){ 
			control = true; 
			eval("this."+ 
			o.type+"('"+obj.text[0]+"');"); 
		} else{ 
			control = false; 
			eval("this."+ 
			o.type+"('"+obj.text[1]+"');");
		} 
	} 
})(jQuery);