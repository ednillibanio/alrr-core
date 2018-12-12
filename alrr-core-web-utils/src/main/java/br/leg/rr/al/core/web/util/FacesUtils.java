package br.leg.rr.al.core.web.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationCase;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// TODO: Depois tem que colocar comentários nos métodos para saber o que cada um faz melhor. Além disso, saber qual o escopo que será definido. por enquanto é session, mas poderia ser request talvez.
@Named
@SessionScoped
public class FacesUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -560525635343950446L;

	public static FacesContext getFacesContextInstance() {
		return FacesContext.getCurrentInstance();
	}

	public static ELContext getELContext() {
		return FacesContext.getCurrentInstance().getELContext();
	}

	public static Application getApplication() {
		return FacesContext.getCurrentInstance().getApplication();
	}

	public static ExpressionFactory getExpressionFactory() {
		return FacesUtils.getApplication().getExpressionFactory();
	}

	public static HttpServletRequest getServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static HttpServletResponse getServletResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}

	public static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public static MethodExpression createMethodExpression(String action) {
		return getExpressionFactory().createMethodExpression(FacesUtils.getELContext(), action, String.class,
				new Class[] { ActionEvent.class });
	}

	public static MethodExpressionActionListener createMethodExpressionActionListener(String actionListenerName) {
		return new MethodExpressionActionListener(createMethodExpression(actionListenerName));
	}

	public static String createId() {
		return FacesContext.getCurrentInstance().getViewRoot().createUniqueId();
	}

	public static UIViewRoot getViewRoot() {
		return FacesContext.getCurrentInstance().getViewRoot();
	}

	public static void setViewRoot(UIViewRoot viewRoot) {
		FacesContext.getCurrentInstance().setViewRoot(viewRoot);
	}

	public static ConfigurableNavigationHandler getNavigationHandler() {
		return (ConfigurableNavigationHandler) getApplication().getNavigationHandler();
	}

	public static NavigationCase getNavigationCase(String outcome) {
		return getNavigationHandler().getNavigationCase(getFacesContextInstance(), null, outcome);
	}

	public static String getToViewId(String outcome) {
		return getNavigationCase(outcome).getToViewId(getFacesContextInstance());
	}

	public static FaceletContext getFaceletContext(String outcome) {
		return (FaceletContext) getFacesContextInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
	}

	public static Locale getLocale() {
		return getViewRoot().getLocale();
	}

	public static void setLocale(String language, String country) {
		getViewRoot().setLocale(new Locale(language, country));
	}

	public static void includeFacelet(UIComponent parent, String outcome) {
		String url = getToViewId(outcome);
		try {
			getFaceletContext(outcome).includeFacelet(parent, url);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}

	public static HttpSession getSession(Boolean create) {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(create);
	}

	public String getFullContextPath() {
		return getServletRequest().getRequestURL().substring(0, getServletRequest().getRequestURL().indexOf(":"))
				+ "://" + getServletRequest().getServerName() + ":" + getServletRequest().getServerPort()
				+ getServletRequest().getContextPath();
	}

	/**
	 * Verifica se o usuário tem permissão de executar uma action ou actionListener
	 * 
	 * @param outcome valor que encontra mapeado no faces-config.xml que aponta para
	 *                toViewId
	 * @return Retorna true se usuário tem permissão ou false caso contrário.
	 */
	public static boolean isUserInRole(String permission) {
		return FacesUtils.getServletRequest().isUserInRole(permission);
	}

	/**
	 * Verifica se o usuário tem permissão de acessar uma View.
	 * 
	 * @param outcome valor que encontra mapeado no faces-config.xml que aponta para
	 *                toViewId
	 * @return Retorna true se usuário tem permissão ou false caso contrário.
	 */
	public static boolean hasViewPermission(String outcome) {
		return FacesUtils.getServletRequest().isUserInRole(FacesUtils.getToViewId(outcome));
	}

	public static Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	/**
	 * TODO: Colocar comentário para saber do que se trata. Já não lembro mais.
	 * 
	 * @param actionListenerName
	 * @return
	 */
	public static AjaxBehavior createAjaxBeheavior(String actionListenerName) {

		return PrimeFacesUtils.createAjaxBeheavior(actionListenerName);
	}

	/**
	 * Atualiza o componente informado.
	 * 
	 * @param arg0 id do componente que deve ser atualizado.
	 */
	public static void update(String... arg0) {
		PrimeFacesUtils.update(arg0);
	}

	/**
	 * Atualiza o componente informado.
	 * 
	 * @param name nome do componente que deve ser atualizado.
	 */
	public static void reset(String... arg0) {
		PrimeFacesUtils.reset(arg0);
	}

	/**
	 * Fecha o Dialog desejado que encontra na view.
	 * 
	 * @param valor valor opcional para passar de volta para o evento dialogReturn.
	 */
	public static void closeDialog(Object valor) {
		PrimeFacesUtils.closeDialog(valor);
	}

	/**
	 * Abre o Dialogo.
	 * 
	 * @param widgetVar valor da variavel 'widgetVar'.
	 */
	public static void showDialog(String widgetVar) {
		execute("PF('".concat(widgetVar.concat("').show();")));
	}

	/**
	 * Fecha o Dialogo.
	 * 
	 * @param widgetVar valor da variavel 'widgetVar'.
	 */
	public static void hideDialog(String widgetVar) {
		execute("PF('".concat(widgetVar.concat("').hide();")));
	}

	/**
	 * Método que chama o evento start() do componente. Exemplo:
	 * PF('compWV').start();
	 * 
	 * @param comp widgetVar do componenten
	 */
	public static void start(String comp) {
		execute("PF('".concat(comp.concat("').start();")));
	}

	/**
	 * Abre uma view num Dialog.
	 * 
	 * @param outcome outcome usado para encontrar um navigation case.
	 */
	public static void openDialog(String outcome) {
		PrimeFacesUtils.openDialog(outcome);
	}

	public static void openDynamic(String outcome, Map<String, Object> options, Map<String, List<String>> params) {
		PrimeFacesUtils.openDynamic(outcome, options, params);
	}

	public static UIComponent findComponent(String componentId) {

		UIViewRoot root = getViewRoot();
		UIComponent c = findComponent(root, componentId);
		return c;
	}

	public static ViewHandler getViewHandler() {
		return getApplication().getViewHandler();
	}

	public static Map<String, Object> getViewMap() {
		return getViewRoot().getViewMap();
	}

	public static UIViewRoot createView() {
		return getViewHandler().createView(getFacesContextInstance(), getViewRoot().getViewId());

	}

	private static UIComponent findComponent(UIComponent component, String id) {
		if (id.equals(component.getId())) {
			return component;
		}

		Iterator<UIComponent> kids = component.getFacetsAndChildren();
		while (kids.hasNext()) {
			UIComponent kid = kids.next();
			UIComponent found = findComponent(kid, id);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	/**
	 * Mostra uma mensagem em um dialog.
	 * 
	 * @param msg FacesMessage a ser mostrada.
	 */
	public static void showMessageInDialog(FacesMessage msg) {
		PrimeFacesUtils.showMessageInDialog(msg);
	}

	public static Map<String, Object> getRequestMap() {
		return getExternalContext().getRequestMap();
	}

	public static Map<String, String> getRequestParameterMap() {
		return getExternalContext().getRequestParameterMap();
	}

	/**
	 * Limpa todos os campos de entrada e saída de dados.
	 */
	public static void resetView() {
		UIViewRoot viewRoot = createView();
		setViewRoot(viewRoot);
		getFacesContextInstance().renderResponse();
	}

	/**
	 * Executa comando javascript após o retorno do ajax.
	 * 
	 * @param arg0
	 */
	public static void execute(String arg0) {
		PrimeFacesUtils.execute(arg0);
	}

	public static void addCallbackParam(String arg0, boolean arg1) {
		PrimeFacesUtils.ajax().addCallbackParam(arg0, arg1);

	}

	public static boolean isPostback() {
		return getFacesContextInstance().isPostback();
	}

	public static String getViewId() {
		return getViewRoot().getViewId();
	}

	/**
	 * Método utilizado para limpar as mensagens da tela. Pode ser utilizado, por
	 * exemplo, após chamar um dialog a partir de uma view que possui muitas
	 * mensagens referentes a uma transação anterior.<br/>
	 * Isso evitará propagação de mensagens que não condiz com a transação em
	 * execução.
	 */
	public static void clearMessages() {
		getFacesContextInstance().getMessages().remove();
	}

	/**
	 * @see javax.servlet.ServletContext.getContextPath()
	 */
	public static String getContextPath() {
		return getServletContext().getContextPath();
	}

	public static void redirect(String url) throws IOException {
		getExternalContext().redirect(url);
	}

	/**
	 * @see javax.faces.component.UIComponent.getAttributes()
	 */
	public static Map<String, Object> getAttributes(ActionEvent event) {
		return event.getComponent().getAttributes();
	}

}
