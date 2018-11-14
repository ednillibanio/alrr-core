/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.domain;

import java.util.EnumMap;

import br.leg.rr.al.core.jpa.BasicEnum;

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

	/**
	 * Contém os valores das chaves que serão armazenados no banco de dados.
	 * 
	 * @return Retorna uma lista com todos os StatusType.
	 */
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
