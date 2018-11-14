package br.leg.rr.al.core.web.converters;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.NumberConverter;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.leg.rr.al.core.helper.DinheiroUtils;
import br.leg.rr.al.core.web.util.FacesUtils;

@Named
@RequestScoped
public class DinheiroConverter extends NumberConverter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7758643039350909376L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent component, String value) {

		if (!StringUtils.isBlank(value)) {
			try {
				return DinheiroUtils.unformat(value);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return value;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		BigDecimal dinheiro = (BigDecimal) value;

		try {
			return DinheiroUtils.format(dinheiro.doubleValue(), FacesUtils.getLocale());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (String) value;
	}
}
