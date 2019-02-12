/**
 * Usado para fechar o Dialog de Manual quando clica fora do dialog. Listener to
 * trigger modal close, when clicked on dialog mask.
 */
$(document).ready(function() {
	$("body").on("click", '.ui-dialog-mask', function() {
		idModal = this.id;
		idModal = idModal.replace("_modal", "");
		getWidgetVarById(idModal).hide();
	})
});

/**
 * Usado para quando clicar fora do Dialog ele fechar Returns the
 * PrimefacesWidget from ID
 * 
 * @param id
 * @returns {*}
 */
function getWidgetVarById(id) {
	for ( var propertyName in PrimeFaces.widgets) {
		var widget = PrimeFaces.widgets[propertyName];
		if (widget && widget.id === id) {
			return widget;
		}
	}
}