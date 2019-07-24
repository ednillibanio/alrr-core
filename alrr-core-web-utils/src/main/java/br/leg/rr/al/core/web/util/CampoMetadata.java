package br.leg.rr.al.core.web.util;

/**
 * 
 * @author Ednil
 *
 */
public class CampoMetadata<T> {

	private T value;

	private Boolean required = false;
	private Boolean disabled = false;

	/**
	 * @return the required
	 */
	public Boolean getRequired() {
		return required;
	}

	/**
	 * Default valor é <code>true</code>.
	 * 
	 * @param required valor a ser definido.
	 */
	public void setRequired(Boolean required) {
		this.required = required;
	}

	/**
	 * 
	 * @return the disabled
	 */
	public Boolean getDisabled() {
		return disabled;
	}

	/**
	 * Default valor é <code>false</code>.
	 * @param disabled the disabled to set
	 */
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}
}
