package br.leg.rr.al.core.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.behavior.AjaxBehavior;

import org.primefaces.PrimeFaces;
import org.primefaces.PrimeFaces.Ajax;
import org.primefaces.behavior.ajax.AjaxBehaviorListenerImpl;

/**
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 */
class PrimeFacesUtils {

	/**
	 * Método que chama o ajax update do componente primefaces e atualiza todos os
	 * componentes pelos clientIds.
	 * 
	 * 
	 * @param arg0 cliendId
	 */
	public static void update(String... arg0) {
		PrimeFaces.current().ajax().update(arg0);
	}

	public static void execute(String statement) {
		PrimeFaces.current().executeScript(statement);
	}

	public static void reset(String... arg0) {
		PrimeFaces.current().resetInputs(arg0);
	}

	public static Ajax ajax() {
		return PrimeFaces.current().ajax();
	}

	public static void closeDialog(Object arg0) {
		PrimeFaces.current().dialog().closeDynamic(arg0);
	}

	public static void openDialog(String outcome) {
		PrimeFaces.current().dialog().openDynamic(outcome);
	}

	public static void showMessageInDialog(FacesMessage msg) {
		PrimeFaces.current().dialog().showMessageDynamic(msg);
	}

	/**
	 * TODO: Colocar comentário para saber do que se trata. Já não lembro mais.
	 * 
	 * @param actionListenerName
	 * @return
	 */
	public static AjaxBehavior createAjaxBeheavior(String actionListenerName) {
		AjaxBehavior ajaxBehavior = (AjaxBehavior) FacesUtils.getApplication().createBehavior(AjaxBehavior.BEHAVIOR_ID);
		ajaxBehavior.addAjaxBehaviorListener(
				new AjaxBehaviorListenerImpl(FacesUtils.createMethodExpression(actionListenerName), null));

		return ajaxBehavior;
	}

}
