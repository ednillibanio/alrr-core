package br.leg.rr.al.core.web.converters;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.leg.rr.al.core.jpa.BaseEntityStatus;

@Named(value = "entityConverter")
@RequestScoped
public class EntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null) {
			Object o = this.getAttributesFrom(component).get(value);
			if (o != null) {
				return o;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {

		if (value != null && !"".equals(value) && value instanceof BaseEntityStatus) {

			@SuppressWarnings("rawtypes")
			BaseEntityStatus entity = (BaseEntityStatus) value;

			// adiciona item como atributo do componente
			this.addAttribute(component, entity);
			Serializable codigo = entity.getId();

			return (codigo != null ? String.valueOf(codigo) : "");
		}

		return "";

	}

	@SuppressWarnings("rawtypes")
	protected void addAttribute(UIComponent component, BaseEntityStatus o) {
		if (o.getId() != null) {
			this.getAttributesFrom(component).put(o.getId().toString(), o);
		}
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}
}
