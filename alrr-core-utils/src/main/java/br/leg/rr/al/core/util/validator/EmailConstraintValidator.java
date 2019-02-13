/**
 * 
 */
package br.leg.rr.al.core.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;

import br.leg.rr.al.core.CoreUtilsValidationMessages;
import br.leg.rr.al.core.utils.MessageUtils;

/**
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * 
 * @since 1.0.0
 */
public class EmailConstraintValidator implements ConstraintValidator<Email, String> {

	String msg;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(Email constraintAnnotation) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		EmailValidator validator = EmailValidator.getInstance();

		if (value != null && !validator.isValid(value)) {
			String msg = MessageUtils.formatMessage(CoreUtilsValidationMessages.EMAIL_INVALIDO);
			context.disableDefaultConstraintViolation();
			// build new violation message and add it
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		return true;

	}

}
