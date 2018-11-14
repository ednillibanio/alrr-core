/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.domain;

public enum SistemaLocale {

	pt_BR("pt", "BR", "enum.idioma.portugues"), en("en", "US", "enum.idioma.ingles"), es("es", "ES",
			"enum.idioma.espanhol");

	private String label;
	private String language;
	private String country;

	private SistemaLocale(String language, String country, String label) {
		this.label = label;
		this.language = language;
		this.country = country;

	}

	public String getLabel() {
		return label;
	}

	public String getLanguage() {
		return language;
	}

	public String getCountry() {
		return country;
	}

}
