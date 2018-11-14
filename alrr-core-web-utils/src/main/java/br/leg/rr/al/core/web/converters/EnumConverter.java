/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.web.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "enumConverter")
public class EnumConverter implements Converter {

	private static final String ATTRIBUTE_ENUM_TYPE = "EnumConverter.enumType";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		Class<Enum> enumType = (Class<Enum>) component.getAttributes().get(ATTRIBUTE_ENUM_TYPE);
		try {
			return Enum.valueOf(enumType, value);
		} catch (IllegalArgumentException e) {
			// TODO: criar mensagem em properties.
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_FATAL,
					"O valor não é um tipo de enum: " + enumType, "O valor não é um tipo de enum: " + enumType));
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		} else if (value instanceof Enum) {
			component.getAttributes().put(ATTRIBUTE_ENUM_TYPE, value.getClass());
			return ((Enum<?>) value).name();
		} else {
			// TODO: colocar as mensagens em properties.
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_FATAL,
					"Valor não é um enum: " + value.getClass(), "Valor não é um enum: " + value.getClass()));
		}
	}
}
