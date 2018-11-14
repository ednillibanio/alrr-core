/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.web.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.leg.rr.al.core.utils.ResourceBundleUtils;

public abstract class FacesBundleMessagesUtil extends ResourceBundleUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8545101016934345420L;

	/**
	 * 
	 * @param facesMessage
	 */
	public void addMessage(FacesMessage facesMessage) {
		FacesUtils.getFacesContextInstance().addMessage(null, facesMessage);
	}

	/**
	 * 
	 * @param component
	 * @param facesMessage
	 */
	private void addMessage(UIComponent component, FacesMessage facesMessage) {
		FacesUtils.getFacesContextInstance().addMessage(component.getClientId(), facesMessage);
	}

	/**
	 * 
	 * @param msg
	 */
	public void addInfo(String key) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_INFO, key));
	}

	/**
	 * 
	 * @param resource
	 *            Indica se deve buscar a mensagem no ResourceBundle.
	 * @param key
	 *            Pode ser a mensagem ou a chave (value) para buscar no
	 *            ResourceBundle definido na aplicação.
	 */
	public void addInfo(String key, Object... params) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_INFO, key));
	}

	public void addInfo(UIComponent component, String key) {
		addMessage(component, createFacesMessage(FacesMessage.SEVERITY_INFO, key));
	}

	public void addInfo(Set<? extends ConstraintViolation<?>> violations) {

		for (ConstraintViolation<?> violation : violations) {
			if (violation.getPropertyPath() != null) {

				UIComponent comp = FacesUtils.findComponent(violation.getPropertyPath().toString());

				if (comp != null) {
					addError(comp.getClientId(), violation.getMessage());
				} else {
					addError(violation.getMessage());
				}
			} else {
				addError(violation.getMessage());
			}
		}

	}

	public void addError(String key) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_ERROR, key));
	}

	/**
	 * 
	 * @param resource
	 *            Indica se deve buscar a mensagem no ResourceBundle.
	 * @param key
	 *            Pode ser a mensagem ou a chave (value) para buscar no
	 *            ResourceBundle definido na aplicação.
	 */
	public void addError(String key, Object... params) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_ERROR, key, params));
	}

	public void addError(UIComponent component, String key) {
		addMessage(component, createFacesMessage(FacesMessage.SEVERITY_ERROR, key));
	}

	public void addError(Set<? extends ConstraintViolation<?>> violations) {

		for (ConstraintViolation<?> violation : violations) {
			if (violation.getPropertyPath() != null) {

				UIComponent comp = FacesUtils.findComponent(violation.getPropertyPath().toString());

				if (comp != null) {
					addError(comp.getClientId(), violation.getMessage());
				} else {
					addError(violation.getMessage());
				}
			} else {
				addError(violation.getMessage());
			}
		}

	}

	/**
	 * 
	 * @param key
	 *            Pode ser a mensagem ou a chave (value) para buscar no
	 *            ResourceBundle definido na aplicação.
	 */
	public void addWarn(String key) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_WARN, key));
	}

	/**
	 * 
	 * @param component
	 * @param key
	 *            Pode ser a mensagem ou a chave (value) para buscar no
	 *            ResourceBundle definido na aplicação.
	 */
	public void addWarn(UIComponent component, String key) {
		addMessage(component, createFacesMessage(FacesMessage.SEVERITY_WARN, key));
	}

	/**
	 * 
	 * @param key
	 *            Pode ser a mensagem ou a chave (value) para buscar no
	 *            ResourceBundle definido na aplicação.
	 */
	public void addFatal(String key) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_FATAL, key));
	}

	/**
	 * 
	 * @param component
	 * @param key
	 *            Pode ser a mensagem ou a chave (value) para buscar no
	 *            ResourceBundle definido na aplicação.
	 */
	public void addFatal(UIComponent component, String key) {
		addMessage(component, createFacesMessage(FacesMessage.SEVERITY_FATAL, key));
	}

	/**
	 * 
	 * @param severity
	 * @param msg
	 * @return
	 */
	private FacesMessage createFacesMessage(Severity severity, String msg) {
		return new FacesMessage(severity, msg, msg);
	}

	/**
	 * 
	 * @param severity
	 * @param key
	 * @param params
	 * @return
	 */
	private FacesMessage createFacesMessage(Severity severity, String key, Object... params) {
		String msg = getMessage(key, params);
		return new FacesMessage(severity, msg, msg);
	}

	/**
	 * 
	 * @param resource
	 * @param key
	 * @return
	 */
	public FacesMessage createError(String key) {
		String msg = getMessage(key);
		return createFacesMessage(FacesMessage.SEVERITY_ERROR, msg);
	}

	/**
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public FacesMessage createError(String key, Object... params) {
		String msg = getMessage(key, params);
		return createFacesMessage(FacesMessage.SEVERITY_ERROR, msg);
	}

	/**
	 * 
	 * @param resource
	 * @param key
	 * @param params
	 * @return
	 */
	// FIXME Nao está associando ao componente. Arrumar depois
	public FacesMessage createError(UIComponent component, String key, Object... params) {
		String msg = getMessage(key, params);
		return createFacesMessage(FacesMessage.SEVERITY_ERROR, msg);
	}

	/**
	 * Faz validação de um campo conforme o grupo informado. A validação é realizada
	 * no nível de campo. É utilizada nos campos das jsf. Ex.
	 * <p:inputText id="enome" value="#{feriadoController.entity.nome}" maxlength=
	 * "150" size="50" required="true" validator=
	 * "#{feriadoController.verificaSeExiste}" /> Neste caso, quando uma action for
	 * chamada, essa validação irá acontecer antes da action ser executada.
	 * 
	 * @param validator
	 *            instancia do validador que será chamado
	 * @param beanType
	 *            A entidade que será pesquisado o campo. Ex. ValidFeriado.class
	 * @param propertyName
	 *            Nome do campo a ser executado a validação. Ex. "nome"
	 * @param value
	 *            Valor que deve ser validado. Ex. "Desenvolvedor XPTO"
	 * @param group
	 *            O grupo é útil no caso que você queira que o campo seja apenas
	 *            validado em determinados momento. Por exemplo, verificar se nome
	 *            já existe no banco de dados. Não é necessário executar novamente
	 *            após validação. Tem regras especificas
	 * @return
	 */
	public <T> List<FacesMessage> createError(Validator validator, Class<T> beanType, String propertyName, Object value,
			Class<?> group) {

		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Set<ConstraintViolation<T>> constraints = validator.validateValue(beanType, propertyName, value, group);

		if (constraints.size() > 0) {
			for (ConstraintViolation<?> constraint : constraints) {
				msgs.add(createError(constraint.getMessage()));
			}
		}

		return msgs;

	}

	public <T> List<FacesMessage> createError(Set<ConstraintViolation<T>> violations) {

		List<FacesMessage> msgs = new ArrayList<FacesMessage>();

		if (violations.size() > 0) {
			for (ConstraintViolation<?> violation : violations) {
				msgs.add(createError(violation.getMessage()));
			}
		}

		return msgs;

	}

	/**
	 * 
	 * @param bundleName
	 * @return
	 */
	@Override
	protected ResourceBundle getResourceBundle(String bundleName) {
		return FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(),
				bundleName);
	}

	@Override
	protected ResourceBundle getResourceBundle() {
		return FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(),
				getBundleName());
	}

	/**
	 * Passa uma mensagem para outra view. TODO: Tem que ver como que usa. Pois não
	 * funcionou para entrar no index quando altera a senha.
	 */
	public void activeKeepMessages() {
		FacesUtils.getFlash().setKeepMessages(true);
	}

}
