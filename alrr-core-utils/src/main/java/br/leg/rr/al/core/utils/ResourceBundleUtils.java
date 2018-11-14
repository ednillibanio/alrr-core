package br.leg.rr.al.core.utils;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

public class ResourceBundleUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1929218739188903273L;

	/**
	 * Define o nome do bundleName
	 */
	private String bundleName;

	public String getMessage(String key, Object... params) {

		try {
			return MessageUtils.formatMessage(getMessage(getResourceBundle(), key), params);

		} catch (MissingResourceException e) {
			e.getMessage();

		} catch (Exception e) {
			e.getMessage();
		}

		return null;

	}

	/**
	 * 
	 * @param bundleName
	 * @param key
	 * @return
	 */
	public String getMessage(String key) {
		return getMessage(getBundleName(), key, (Object[]) null);
	}

	/**
	 * 
	 * @param bundle
	 * @param key
	 * @return
	 */
	public String getMessage(ResourceBundle bundle, String key) {
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
	public String getMessage(Locale locale, String messageBundle, String key) {
		if (StringUtils.isNotBlank(messageBundle) && locale != null) {

			ResourceBundle bundle = ResourceBundle.getBundle(messageBundle, locale);
			if (bundle.containsKey(key)) {
				return bundle.getString(key);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param bundleName
	 * @return
	 */
	protected ResourceBundle getResourceBundle(String bundleName) {
		return ResourceBundle.getBundle(bundleName);
	}

	protected ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle(getBundleName());
	}

	public String getBundleName() {
		return bundleName;
	}

	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}

}
