/**
 * 
 */
package br.leg.rr.al.core.util.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Ednil Libanio da Costa Junior
 * @date 11-04-2018
 */
@Target(value = { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { EmailConstraintValidator.class })
@Documented
public @interface Email {
	String message() default "Please provide a valid email address";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
