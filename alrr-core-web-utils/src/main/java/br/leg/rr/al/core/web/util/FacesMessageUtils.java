package br.leg.rr.al.core.web.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;

import br.leg.rr.al.core.utils.MessageUtils;

public class FacesMessageUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8545101016934345420L;

	/**
	 * 
	 * @param component
	 * @param facesMessage
	 */
	private static void addMessage(UIComponent component, FacesMessage facesMessage) {
		FacesUtils.getFacesContextInstance().addMessage(component.getClientId(), facesMessage);
	}

	/**
	 * 
	 * @param severity
	 * @param msg
	 * @return
	 */
	private static FacesMessage createFacesMessage(Severity severity, String msg) {
		return new FacesMessage(severity, msg, msg);

	}

	private static ResourceBundle getFacesResourceBundle(String bundleName) {
		return FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(),
				bundleName);
	}

	/**
	 * 
	 * @param facesMessage
	 */
	public static void addMessage(FacesMessage facesMessage) {
		FacesUtils.getFacesContextInstance().addMessage(null, facesMessage);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void addInfo(String msg) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_INFO, msg));
	}

	/**
	 * 
	 * @param resource  Indica se deve buscar a mensagem no ResourceBundle.
	 * @param component
	 * @param key       Pode ser a mensagem ou a chave (value) para buscar no
	 *                  ResourceBundle definido na aplicação.
	 */
	public static void addInfo(String bundleName, String key) {

		addMessage(createFacesMessage(FacesMessage.SEVERITY_INFO, getMessage(bundleName, key)));
	}

	// TODO comentar metodo. tem varios aqui que precisam de comentários.
	public static void addInfo(String msg, Object... params) {

		addMessage(createFacesMessage(FacesMessage.SEVERITY_INFO, MessageUtils.formatMessage(msg, params)));
	}

	/**
	 * 
	 * @param resource Indica se deve buscar a mensagem no ResourceBundle.
	 * @param key      Pode ser a mensagem ou a chave (value) para buscar no
	 *                 ResourceBundle definido na aplicação.
	 */
	public static void addInfo(String bundleName, String key, Object... params) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_INFO, getMessage(bundleName, key, params)));
	}

	/**
	 * 
	 * @param resource  Indica se deve buscar a mensagem no ResourceBundle.
	 * @param component
	 * @param key       Pode ser a mensagem ou a chave (value) para buscar no
	 *                  ResourceBundle definido na aplicação.
	 */
	public static void addInfo(UIComponent component, String bundleName, String key) {
		addMessage(component, createFacesMessage(FacesMessage.SEVERITY_INFO, getMessage(bundleName, key)));
	}

	public static void addInfo(UIComponent component, String msg) {
		addMessage(component, createFacesMessage(FacesMessage.SEVERITY_INFO, msg));
	}

	public static void addInfo(Set<? extends ConstraintViolation<?>> violations) {

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

	public static void addError(String msg) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_ERROR, msg));
	}

	public static void addError(String msg, Object... params) {

		addMessage(createFacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.formatMessage(msg, params)));
	}

	/**
	 * 
	 * @param resource  Indica se deve buscar a mensagem no ResourceBundle.
	 * @param component
	 * @param key       Pode ser a mensagem ou a chave (value) para buscar no
	 *                  ResourceBundle definido na aplicação.
	 */
	public static void addError(String bundleName, String key) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_ERROR, getMessage(bundleName, key)));
	}

	/**
	 * 
	 * @param resource Indica se deve buscar a mensagem no ResourceBundle.
	 * @param key      Pode ser a mensagem ou a chave (value) para buscar no
	 *                 ResourceBundle definido na aplicação.
	 */
	public static void addError(String bundleName, String key, Object... params) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_ERROR, getMessage(bundleName, key, params)));
	}

	/**
	 * 
	 * @param resource  Indica se deve buscar a mensagem no ResourceBundle.
	 * @param component
	 * @param key       Pode ser a mensagem ou a chave (value) para buscar no
	 *                  ResourceBundle definido na aplicação.
	 */
	public static void addError(UIComponent component, String bundleName, String key) {
		addMessage(component, createFacesMessage(FacesMessage.SEVERITY_ERROR, getMessage(bundleName, key)));
	}

	public static void addError(UIComponent component, String msg) {
		addMessage(component, createFacesMessage(FacesMessage.SEVERITY_ERROR, msg));
	}

	public static void addError(Set<? extends ConstraintViolation<?>> violations) {

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
	 * @param msg
	 */
	public static void addWarn(String msg) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_WARN, msg));
	}

	/**
	 * 
	 * @param resource Indica se deve buscar a mensagem no ResourceBundle.
	 * @param key      Pode ser a mensagem ou a chave (value) para buscar no
	 *                 ResourceBundle definido na aplicação.
	 */
	public static void addWarn(String bundleName, String key) {

		addMessage(createFacesMessage(FacesMessage.SEVERITY_WARN, getMessage(bundleName, key)));
	}

	/**
	 * 
	 * @param resource  Indica se deve buscar a mensagem no ResourceBundle.
	 * @param component
	 * @param key       Pode ser a mensagem ou a chave (value) para buscar no
	 *                  ResourceBundle definido na aplicação.
	 */
	public static void addWarn(String bundleName, UIComponent component, String key) {

		addMessage(component, createFacesMessage(FacesMessage.SEVERITY_WARN, getMessage(bundleName, key)));
	}

	/**
	 * 
	 * @param resource Indica se deve buscar a mensagem no ResourceBundle.
	 * @param key      Pode ser a mensagem ou a chave (value) para buscar no
	 *                 ResourceBundle definido na aplicação.
	 */
	public static void addFatal(String bundleName, String key) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_FATAL, getMessage(bundleName, key)));
	}

	/**
	 * 
	 * @param resource Indica se deve buscar a mensagem no ResourceBundle.
	 * @param value    Pode ser a mensagem ou a chave (value) para buscar no
	 *                 ResourceBundle definido na aplicação.
	 */
	public static void addFatal(String message) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_FATAL, message));
	}

	public static void addFatal(String message, Object... params) {
		addMessage(createFacesMessage(FacesMessage.SEVERITY_FATAL, MessageUtils.formatMessage(message, params)));
	}

	/**
	 * 
	 * @param resource  Indica se deve buscar a mensagem no ResourceBundle.
	 * @param component
	 * @param key       Pode ser a mensagem ou a chave (value) para buscar no
	 *                  ResourceBundle definido na aplicação.
	 */
	public static void addFatal(String bundleName, UIComponent component, String key) {
		addMessage(component, createFacesMessage(FacesMessage.SEVERITY_FATAL, getMessage(bundleName, key)));
	}

	/**
	 * 
	 * @param resource
	 * @param key
	 * @return
	 */
	public static FacesMessage createError(String msg) {
		return createFacesMessage(FacesMessage.SEVERITY_ERROR, msg);
	}

	public static FacesMessage createError(String bundleName, String key) {
		return createFacesMessage(FacesMessage.SEVERITY_ERROR, getMessage(bundleName, key));
	}

	public static FacesMessage createError(String bundleName, String key, Object... params) {
		return createFacesMessage(FacesMessage.SEVERITY_ERROR, getMessage(bundleName, key, params));
	}

	/**
	 * 
	 * @param resource
	 * @param key
	 * @param params
	 * @return
	 */
	public static FacesMessage createError(UIComponent component, String bundleName, String key, Object... params) {

		return createFacesMessage(FacesMessage.SEVERITY_ERROR, getMessage(bundleName, key, params));
	}

	/**
	 * Cria um FaceMessage de erro para o componente informado.
	 * 
	 * @param component receberá a mensagem.
	 * @param msg       mensagem definida ao componente.
	 * @return retorna o FacesMessage criado.
	 */
	public static FacesMessage createError(UIComponent component, String msg) {

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
	 * @param validator    instancia do validador que será chamado
	 * @param beanType     A entidade que será pesquisado o campo. Ex.
	 *                     ValidFeriado.class
	 * @param propertyName Nome do campo a ser executado a validação. Ex. "nome"
	 * @param value        Valor que deve ser validado. Ex. "Desenvolvedor XPTO"
	 * @param group        O grupo é útil no caso que você queira que o campo seja
	 *                     apenas validado em determinados momento. Por exemplo,
	 *                     verificar se nome já existe no banco de dados. Não é
	 *                     necessário executar novamente após validação. Tem regras
	 *                     especificas
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

	public static String getMessage(String bundleName, String key) {
		if (StringUtils.isNotBlank(bundleName) && key != null) {

			ResourceBundle bundle = getFacesResourceBundle(bundleName);
			if (bundle != null && bundle.containsKey(key)) {
				return bundle.getString(key);
			}
		}
		return null;

	}

	public static String getMessage(String bundleName, String key, Object... params) {

		try {
			String msg = getMessage(bundleName, key);
			return MessageUtils.formatMessage(msg, params);

		} catch (MissingResourceException e) {
			e.getMessage();

		} catch (Exception e) {
			e.getMessage();
		}

		return null;

	}

	/**
	 * 
	 * @param bundle
	 * @param key
	 * @return
	 */
	public static String getMessage(ResourceBundle bundle, String key) {
		if (StringUtils.isNotBlank(key) && bundle != null) {

			if (bundle.containsKey(key)) {
				return bundle.getString(key);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param locale
	 * @param messageBundle
	 * @param key
	 * @return
	 */
	public static String getMessage(Locale locale, String bundleName, String key) {
		if (StringUtils.isNotBlank(bundleName) && locale != null) {

			ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
			if (bundle.containsKey(key)) {
				return bundle.getString(key);
			}
		}
		return null;
	}

	/**
	 * Passa uma mensagem para outra view. TODO: Tem que ver como que usa. Pois não
	 * funcionou para entrar no index quando altera a senha.
	 */
	public static void activeKeepMessages() {
		FacesUtils.getFlash().setKeepMessages(true);
	}

}
