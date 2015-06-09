//jQuery 防止图片另存为(透明图遮盖原图)        
jQuery.fn.protectImage = function(settings) {
	settings = jQuery.extend( {
		image : "../admin/images/blank.png",
		zIndex : 999
	}, settings);
	return this.each(function() {
		var position = $(this).position();
		var height = $(this).height();
		var width = $(this).width();
		$("<img />").attr( {
			width : width,
			height : height,
			src : settings.image
		}).css( {
			//border : "1px solid #f00",
			top : position.top,
			left : position.left,
			position : "absolute",
			zIndex : settings.zIndex
		}).appendTo("body")
	});
};
$(window).bind("load", function() {
	$("img.protect").protectImage();
});