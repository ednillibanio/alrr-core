package br.leg.rr.al.core.jpa;

import java.io.Serializable;
import java.util.EnumMap;

/**
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa Junior</a>
 * @since 1.0.0
 * @param <K>
 */
public interface BasicEnum<K extends Enum<K>> extends Serializable {

	public String getLabel();

	public EnumMap<K, ?> getEnumMap();

}
