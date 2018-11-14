package br.leg.rr.al.core.utils;

import java.util.EnumMap;
import java.util.Iterator;

import br.leg.rr.al.core.jpa.BasicEnum;

// FIXME: Falta tratar exceção quando não encontrar valores.
public final class EnumUtils {

	public static <T, V> T convertToEntityAttribute(EnumMap<? extends Enum<?>, V> map, String valor) {

		if (map != null && valor != null) {
			@SuppressWarnings("unchecked")
			Iterator<T> iterator = (Iterator<T>) map.keySet().iterator();
			T key = null;
			V enumValor = null;
			while (iterator.hasNext()) {
				key = iterator.next();
				enumValor = map.get(key);
				if (valor.equals(enumValor)) {
					return key;
				}
			}
		}
		return null;
	}

	// public static <T, V> V convertToDatabaseColumn(EnumMap<? extends Enum<?>,
	// V> map, BasicEnum key) {
	// return map.get(key);
	// }

	/**
	 * 
	 * @param enumClass
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static String convertToDatabaseColumn(BasicEnum<? extends Enum<?>> enumClass) {
		return (String) enumClass.getEnumMap().get(enumClass);
	}

}
