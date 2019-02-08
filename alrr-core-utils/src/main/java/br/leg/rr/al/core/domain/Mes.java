package br.leg.rr.al.core.domain;

import java.util.EnumMap;

import br.leg.rr.al.core.jpa.BasicEnumType;

/**
 * Enum que representa os meses do ano.
 *
 * @author <a href="mailto:alfredo.furtado@pdcase.com.br">Alfredo Furtado</a>.
 * @version 1.0.0
 */
public enum Mes implements BasicEnumType<Mes> {

	JANEIRO("Janeiro", "1"), FEVEREIRO("Fevereiro", "2"), MARCO("Março", "3"), ABRIL("Abril", "4"), MAIO("Maio", "5"),
	JUNHO("Junho", "6"), JULHO("Julho", "7"), AGOSTO("Agosto", "8"), SETEMBRO("Setembro", "9"),
	OUTUBRO("Outubro", "10"), NOVEMBRO("Novembro", "11"), DEZEMBRO("Dezembro", "12");

	private Mes(String label, String value) {
		this.label = label;
		this.value = value;
	}

	private String label;
	private String value;

	/**
	 * Retorna o mês no formato texto. Ex. Janeiro, Fevereiro, Março.
	 */
	@Override
	public String getLabel() {
		return label;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return label;
	}

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
