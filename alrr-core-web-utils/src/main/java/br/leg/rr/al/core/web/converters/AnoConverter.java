/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.web.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "anoConverter")
public class AnoConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2964722704240929147L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent component, String valorTela) {

		if (valorTela == null || valorTela.toString().trim().equals("")) {
			return null;

		} else {
			return new Integer(valorTela);

		}

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valorTela) {
		if (valorTela == null || valorTela.toString().trim().equals("")) {
			return null;

		} else {
			return valorTela.toString();
		}
	}

}
