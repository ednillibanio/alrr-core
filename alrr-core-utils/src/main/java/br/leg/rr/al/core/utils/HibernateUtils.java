package br.leg.rr.al.core.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

public final class HibernateUtils {

	/**
	 * 
	 * @param validator
	 * @param beanType
	 * @param propertyName
	 * @param value
	 * @param group
	 * @return
	 */
	public static <T> Set<ConstraintViolation<T>> getViolations(Validator validator, Class<T> beanType,
			String propertyName, Object value, Class<?> group) {
		return validator.validateValue(beanType, propertyName, value, group);
	}

	public static <T> Set<ConstraintViolation<T>> getViolations(ValidatorFactory validatorFactory, T entity) {
		return validatorFactory.getValidator().validate(entity);
	}

	public static <T> Set<ConstraintViolation<T>> getViolations(ValidatorFactory validatorFactory, T entity,
			Class<?> group) {
		return validatorFactory.getValidator().validate(entity, group);
	}

	public static <T> Set<ConstraintViolation<T>> getViolations(Validator validator, T entity, Class<?> group) {
		return validator.validate(entity, group);
	}

	/**
	 * Valida a entidade e retorna as violações encontradas. As mensagens são
	 * pesquisadas em um ResourceBundle especifico.
	 * 
	 * @param resourceBundleName
	 *            nome do resource bundle que contém as mensagens.
	 * @param entity
	 *            entidade que será validada
	 * @return retorna uma lista de violações.
	 */
	public static <T> Set<ConstraintViolation<T>> getViolations(String resourceBundleName, T entity) {
		return getValidator(resourceBundleName).validate(entity);

	}

	/**
	 * 
	 * @param factory
	 * @param beanType
	 * @param propertyName
	 * @param value
	 * @param group
	 * @return
	 */
	public static <T> Set<ConstraintViolation<T>> getViolations(ValidatorFactory factory, Class<T> beanType,
			String propertyName, Object value, Class<?> group) {
		return getViolations(factory.getValidator(), beanType, propertyName, value, group);
	}

	/***
	 * 
	 * @param resourceBundleName
	 * @return
	 */
	public static Validator getValidator(String resourceBundleName) {
		return Validation.byDefaultProvider().configure()
				.messageInterpolator(
						new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator(resourceBundleName)))
				.buildValidatorFactory().getValidator();

	}

}
