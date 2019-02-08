package br.leg.rr.al.core.domain;

import java.util.EnumMap;

import br.leg.rr.al.core.jpa.BasicEnum;

/**
 * Enum que representa os tipos de email.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * 
 * @since 1.0.0
 *
 */
public enum EmailType implements BasicEnum<EmailType> {
	PESSOAL("Pessoal"), COBRANCA("Cobrança"), COMERCIAL("Comercial");

	private EmailType(String label) {
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
	public EnumMap<EmailType, String> getEnumMap() {
		EnumMap<EmailType, String> map = new EnumMap<EmailType, String>(EmailType.class);
		map.put(EmailType.PESSOAL, "1");
		map.put(EmailType.COBRANCA, "2");
		map.put(EmailType.COMERCIAL, "3");
		return map;
	}

}
