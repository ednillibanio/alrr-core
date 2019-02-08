package br.leg.rr.al.core.domain;

import java.util.EnumMap;

import br.leg.rr.al.core.jpa.BasicEnum;

/**
 * Enum que representa as situações de um objeto do tipo entidade
 * (EntityStatus).
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * 
 * @since 1.0.0
 *
 * 
 */
public enum StatusType implements BasicEnum<StatusType> {

	ATIVO("Ativo", "A"), INATIVO("Inativo", "I");

	private String label;
	private String sigla;
	EnumMap<StatusType, String> map;

	private StatusType(String label) {
		this.label = label;

	}

	private StatusType(String label, String sigla) {
		this.label = label;
		this.sigla = sigla;
	}

	@Override
	public String getLabel() {
		return label;
	}

	public String getSigla() {
		return sigla;
	}

	@Override
	public String toString() {
		return label;
	}

	@Override
	public EnumMap<StatusType, String> getEnumMap() {
		if (map == null) {
			map = new EnumMap<StatusType, String>(StatusType.class);
			map.put(StatusType.INATIVO, "0");
			map.put(StatusType.ATIVO, "1");
		}
		return map;
	}

}
