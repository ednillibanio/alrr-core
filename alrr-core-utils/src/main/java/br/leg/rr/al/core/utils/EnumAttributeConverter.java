package br.leg.rr.al.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;

import javax.persistence.AttributeConverter;

import br.leg.rr.al.core.jpa.BasicEnum;

public abstract class EnumAttributeConverter<E extends BasicEnum<? extends Enum<?>>>
		implements AttributeConverter<E, String> {

	private Class<E> enumType;

	@SuppressWarnings("unchecked")
	public EnumAttributeConverter() {

		Type c = getClass().getGenericSuperclass();
		Type type = ((ParameterizedType) c).getActualTypeArguments()[0];

		if (Class.class.isAssignableFrom(type.getClass())) {
			enumType = (Class<E>) type;
		} else {
			this.enumType = (Class<E>) ((ParameterizedType) type).getRawType();
		}

	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public String convertToDatabaseColumn(E attribute) {
		if (attribute != null) {
			return attribute.getEnumMap().get(attribute).toString();
		}
		return null;
	}

	@Override
	public E convertToEntityAttribute(String valor) {

		/* Enum constants are in order of declaration. */
		Class<?> sub = enumType.getEnumConstants()[0].getClass();
		Method mth = null;
		EnumMap<? extends Enum<?>, ?> map = null;
		try {
			mth = sub.getDeclaredMethod("getEnumMap");
			map = (EnumMap<?, ?>) mth.invoke(enumType.getEnumConstants()[0]);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return EnumUtils.convertToEntityAttribute(map, valor);

	}

}
