package br.leg.rr.al.core.jpa;

import java.util.EnumMap;

/**
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * @param <K>
 */
public interface BasicEnumType<K extends Enum<K>> extends BasicEnum<K> {

	public String getValue();

	public EnumMap<K, ?> getEnumMap();
}
