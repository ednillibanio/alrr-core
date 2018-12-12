/*******************************************************************************
 * ALE-RR Licença
 * Copyright (C) 2018, ALE-RR
 * Boa Vista, RR - Brasil
 * Todos os direitos reservados.
 * 
 * Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e 
 * não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.web.converters;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.leg.rr.al.core.utils.StringHelper;

/**
 * @author Ednil Libanio da Costa Junior
 * @since 1.0.0
 */
@Named
@RequestScoped
public class CapitalizeFullyConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6768134191858673601L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent component, String value) {

		return StringHelper.capitalizeFully(value);

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		return StringHelper.capitalizeFully(value.toString());

	}

}
