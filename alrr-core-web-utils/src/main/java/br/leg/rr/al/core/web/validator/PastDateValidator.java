/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.web.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.leg.rr.al.core.utils.DataUtils;
import br.leg.rr.al.core.utils.MessageUtils;
import br.leg.rr.al.core.web.CoreWebUtilsValidationMessages;
import br.leg.rr.al.core.web.util.FacesMessageUtils;

@FacesValidator(value = "pastDateValidator")
public class PastDateValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		if (value != null) {
			java.util.Date data = (java.util.Date) value;

			if (!DataUtils.isAntesHoje(data)) {
				String msg = MessageUtils.formatMessage(CoreWebUtilsValidationMessages.DATA_MAIOR_QUE_HOJE);
				throw new ValidatorException(FacesMessageUtils.createError(msg));
			}
		}

	}
}
