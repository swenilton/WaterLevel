function ControllerMenu () {
this.activeScrollTopMenu = function () {

		if( $(window).scrollTop() > 40 ){
			$('#logo').addClass('on_scroll');
		}else{
			$('#logo').removeClass('on_scroll');
		}

	}
}