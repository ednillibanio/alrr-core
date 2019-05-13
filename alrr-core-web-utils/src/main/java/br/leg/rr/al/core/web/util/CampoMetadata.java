package br.leg.rr.al.core.web.util;

/**
 * 
 * @author Ednil
 *
 */
public class CampoMetadata {

	private Boolean required = false;
	private Boolean disabled = false;
	/**
	 * @return the required
	 */
	public Boolean getRequired() {
		return required;
	}
	/**
	 * @param required the required to set
	 */
	public void setRequired(Boolean required) {
		this.required = required;
	}
	/**
	 * @return the disabled
	 */
	public Boolean getDisabled() {
		return disabled;
	}
	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
}
