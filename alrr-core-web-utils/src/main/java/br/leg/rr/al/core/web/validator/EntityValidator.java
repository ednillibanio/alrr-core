package br.leg.rr.al.core.web.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import br.leg.rr.al.core.CoreUtilsValidationMessages;
import br.leg.rr.al.core.jpa.BaseEntityStatus;
import br.leg.rr.al.core.web.util.FacesMessageUtils;

@Named(value = "entityValidator")
public class EntityValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		if (value != null && !"".equals(value) && value instanceof BaseEntityStatus) {

			@SuppressWarnings("rawtypes")
			BaseEntityStatus entity = (BaseEntityStatus) value;
			if (entity.getId() == null) {
				throw new ValidatorException(
						FacesMessageUtils.createError(CoreUtilsValidationMessages.REGISTRO_NAO_SELECIONADO));
			} else {
				int id = (int) entity.getId();
				if (id == 0) {
					throw new ValidatorException(
							FacesMessageUtils.createError(CoreUtilsValidationMessages.REGISTRO_NAO_SELECIONADO));
				}

			}

		}

	}
}
