package br.leg.rr.al.core.domain;

import java.util.EnumMap;

import br.leg.rr.al.core.jpa.BasicEnum;

/**
 * Enum que representa todos os turnos diurnos.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * 
 * @since 1.0.0
 *
 * 
 */
public enum TurnoType implements BasicEnum<TurnoType> {

	MATUTINO("Matutino"), VESPERTINO("Vespertino"), NOTURNO("Noturno"), MADRUGRADA("Madrugada");

	private TurnoType(String label) {
		this.label = label;
	}

	private String label;

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return label;
	}

	@Override
	public EnumMap<TurnoType, ?> getEnumMap() {
		EnumMap<TurnoType, String> map = new EnumMap<TurnoType, String>(TurnoType.class);
		map.put(TurnoType.MATUTINO, "1");
		map.put(TurnoType.VESPERTINO, "2");
		map.put(TurnoType.NOTURNO, "3");
		map.put(TurnoType.MADRUGRADA, "4");
		return map;
	}
}
