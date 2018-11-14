/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.domain;

import java.util.EnumMap;

import br.leg.rr.al.core.jpa.BasicEnum;

public enum Mes implements BasicEnum<Mes> {

	JANEIRO("Janeiro", 1), FEVEREIRO("Fevereiro", 2), MARCO("Março", 3), ABRIL("Abril", 4), MAIO("Maio", 5), JUNHO(
			"Junho", 6), JULHO("Julho", 7), AGOSTO("Agosto", 8), SETEMBRO("Setembro",
					9), OUTUBRO("Outubro", 10), NOVEMBRO("Novembro", 11), DEZEMBRO("Dezembro", 12);

	private Mes(String label, int value) {
		this.label = label;
		this.value = value;
	}

	private String label;
	private int value;

	/**
	 * Retorna o mês no formato texto. Ex. Janeiro, Fevereiro, Março.
	 */
	@Override
	public String getLabel() {
		return label;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return label;
	}

	/**
	 * Contém os valores das chaves que serão armazenados no banco de dados.
	 * 
	 * @return Retorna uma lista com todos os Mes.
	 */
	@Override
	public EnumMap<Mes, String> getEnumMap() {
		EnumMap<Mes, String> map = new EnumMap<Mes, String>(Mes.class);
		map.put(Mes.JANEIRO, "1");
		map.put(Mes.FEVEREIRO, "2");
		map.put(Mes.MARCO, "3");
		map.put(Mes.ABRIL, "4");
		map.put(Mes.MAIO, "5");
		map.put(Mes.JUNHO, "6");
		map.put(Mes.JULHO, "7");
		map.put(Mes.AGOSTO, "8");
		map.put(Mes.SETEMBRO, "9");
		map.put(Mes.OUTUBRO, "10");
		map.put(Mes.NOVEMBRO, "11");
		map.put(Mes.DEZEMBRO, "12");

		return map;
	}

}
