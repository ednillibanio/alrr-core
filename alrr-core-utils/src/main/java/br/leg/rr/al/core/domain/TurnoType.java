/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.domain;

import java.util.EnumMap;

import br.leg.rr.al.core.jpa.BasicEnum;

public enum TurnoType implements BasicEnum<TurnoType> {

	MATUTINO("Matutino"), VESPERTINO("Vespertino"), NOTURNO("Noturno");

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
		map.put(TurnoType.MATUTINO, "M");
		map.put(TurnoType.VESPERTINO, "V");
		map.put(TurnoType.NOTURNO, "N");
		return map;
	}
}
