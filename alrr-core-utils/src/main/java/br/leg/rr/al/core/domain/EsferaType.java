/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
/**
 * @(#) TipoIndice.java.
 *
 * Copyright (c) 2008 PD CASE
 * Belo Horizonte, MG - Brasil
 * Todos os direitos reservados.
 *
 * Este software e confidencial e é propriedade da PD CASE, não é permitida a
 * distribuição/alteração da mesma sem prévia autorização.
 */
package br.leg.rr.al.core.domain;

import java.util.EnumMap;

import br.leg.rr.al.core.jpa.BasicEnum;

/**
 * Enum que representa a esfera da entidade ou do órgão público.
 *
 * @author <a href="mailto:alfredo.furtado@pdcase.com.br">Alfredo Furtado</a>.
 * @version 1.0.0
 */
public enum EsferaType implements BasicEnum<EsferaType> {

	FEDERAL("Federal"), MUNICIPAL("Municipal"), ESTADUAL("Estadual");

	private EsferaType(String label) {
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
	public EnumMap<EsferaType, String> getEnumMap() {
		EnumMap<EsferaType, String> map = new EnumMap<EsferaType, String>(EsferaType.class);
		map.put(EsferaType.FEDERAL, "1");
		map.put(EsferaType.ESTADUAL, "2");
		map.put(EsferaType.MUNICIPAL, "3");
		return map;
	}

}
