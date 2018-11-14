package br.leg.rr.al.core.web.converters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

@Named(value = "ceiConverter")
public class CeiConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent component, String value) {

		if (StringUtils.isNotBlank(value)) {

			value = StringUtils.replace(value, ".", "");
			value = StringUtils.replace(value, "-", "");
			value = StringUtils.replace(value, "/", "");
			value = StringUtils.trimToEmpty(value);
			if (StringUtils.isNotBlank(value) && StringUtils.isNumeric(value)) {
				return new Long(value);
			}
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null && !value.equals("")) {
			String cei = Long.toString((Long) value);
			while (cei.length() != 11)
				cei = "0" + cei;
			Pattern p = Pattern.compile("(\\d\\d)(\\d\\d\\d)(\\d\\d\\d\\d\\d)(\\d)(\\d)");
			Matcher m = p.matcher(cei);
			return m.replaceAll("$1.$2.$3/$4$5");
		}

		return null;
	}

}
