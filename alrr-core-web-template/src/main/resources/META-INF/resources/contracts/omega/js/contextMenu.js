/**
 * 
 */
var currentEvent;
$(document)
		.ready(
				function() {
					PrimeFaces.widget.ContextMenu.prototype.show = function(e) {
						// hide other contextmenus if any
						$(document.body).children('.ui-contextmenu:visible')
								.hide();

						if (e) {
							currentEvent = e;
						}

						var win = $(window), left = e.pageX, top = e.pageY, width = this.jq
								.outerWidth(), height = this.jq.outerHeight();
						// collision detection for window boundaries
						if ((left + width) > (win.width()) + win.scrollLeft()) {
							left = left - width;
						}
						if ((top + height) > (win.height() + win.scrollTop())) {
							top = top - height;
						}

						if (this.cfg.beforeShow) {
							this.cfg.beforeShow.call(this);
						}

						this.jq.css({
							'left' : left,
							'top' : top,
							'z-index' : ++PrimeFaces.zindex
						}).show();

						e.preventDefault();
					};
				});