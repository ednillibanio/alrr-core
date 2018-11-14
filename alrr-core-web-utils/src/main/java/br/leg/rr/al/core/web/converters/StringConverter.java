/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
/**
 * @(#) CpfConverter.java.
 *
 * Copyright (c) 2008 PD CASE
 * Belo Horizonte, MG - Brasil
 * Todos os direitos reservados.
 *
 * Este software e confidencial e é propriedade da PD CASE, não é permitida a
 * distribuição/alteração da mesma sem prévia autorização.
 */
package br.leg.rr.al.core.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

/**
 * Converter para evitar strings vazias
 * 
 * @author <a href="mailto:carls.manoel@pdcase.com.br">Carlos Manoel</a>.
 * @version $Revision: 1.1 $
 */
@Named(value = "stringConverter")
public class StringConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent component, String value) {

		return StringUtils.trimToNull(value);

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		if (value == null) {
			return null;
		}

		return value.toString();

	}

}
