package br.leg.rr.al.core.domain;

import java.util.EnumMap;

import br.leg.rr.al.core.jpa.BasicEnum;

/**
 * Enum que representa os dias da semana.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * 
 * @since 1.0.0
 *
 * 
 */
public enum DiaSemana implements BasicEnum<DiaSemana> {

	DOMINGO("Domingo"), SEGUNDA("Segunda"), TERCA("Terça"), QUARTA("Quarta"), QUINTA("Quinta"), SEXTA("Sexta"),
	SABADO("Sábado");

	private DiaSemana(String label) {
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
	public EnumMap<DiaSemana, String> getEnumMap() {
		EnumMap<DiaSemana, String> map = new EnumMap<DiaSemana, String>(DiaSemana.class);
		map.put(DiaSemana.DOMINGO, "0");
		map.put(DiaSemana.SEGUNDA, "1");
		map.put(DiaSemana.TERCA, "2");
		map.put(DiaSemana.QUARTA, "3");
		map.put(DiaSemana.QUINTA, "4");
		map.put(DiaSemana.SEXTA, "5");
		map.put(DiaSemana.SABADO, "6");
		return map;
	}

}
