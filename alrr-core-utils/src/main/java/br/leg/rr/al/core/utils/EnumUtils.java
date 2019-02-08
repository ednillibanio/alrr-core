package br.leg.rr.al.core.utils;

import java.util.EnumMap;
import java.util.Iterator;

import br.leg.rr.al.core.jpa.BasicEnum;

/**
 * Classe util que oferece recursos para manipular valores dos Enums.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * 
 * @since 1.0.0
 *
 */
public final class EnumUtils {

	/**
	 * Método que converte valor do banco de dados para um Enum.
	 * 
	 * @param map   mapa que contém todos os valores do banco de dados. Pode ser
	 *              obtido apartir do getEnumMap() da classe que implementa
	 *              BasicEnum.
	 * @param valor valor que representa o enum da base de dados.
	 * @return uma instância do enum encontrado a partir do @param valor e
	 * @param map informados. Retorna null caso não encontre o valor na lista (map).
	 */
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

	/**
	 * Método que converte valor do Enum instanciado para valor do banco de dados.
	 * 
	 * @param enumClass instância do Enum
	 * @return valor que correnpondente salvo ou recuperado do banco de dados.
	 */
	/**
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static String convertToDatabaseColumn(BasicEnum<? extends Enum<?>> enumClass) {
		return (String) enumClass.getEnumMap().get(enumClass);
	}

}
