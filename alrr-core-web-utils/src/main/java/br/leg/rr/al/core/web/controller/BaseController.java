package br.leg.rr.al.core.web.controller;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import br.leg.rr.al.core.web.util.FacesUtils;

/**
 * FIXME: Arrumar para adaptar a realidade da ALE.
 * 
 * @author ednil
 *
 */
public abstract class BaseController implements Serializable {

	protected static Marker fatal = MarkerFactory.getMarker("FATAL");
	private static final long serialVersionUID = -7033073413761404445L;

	/**
	 * Verifica se o usuário tem permissão de
	 * 
	 * @param outcome
	 * @return
	 */
	public boolean isUserInRole(String permission) {

		// TODO: Essa mensagem não é lançada quando não há permissao. Ver como
		// fazer para disparar.
		if (StringUtils.isBlank(permission)) {
			// FacesMessageUtil.addErrorMessage(new
			// NullArgumentException("permissao").toString());
		}
		return FacesUtils.isUserInRole(permission);

	}

	/**
	 * 
	 * @param outcome
	 * @return
	 */
	public boolean hasViewPermission(String outcome) {

		if (StringUtils.isNotBlank(outcome) && StringUtils.isNotEmpty(outcome)) {
			return FacesUtils.hasViewPermission(outcome);
		}
		return false;

	}

}
