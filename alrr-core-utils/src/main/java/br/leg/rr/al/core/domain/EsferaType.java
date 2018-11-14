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

/**
 * Tipos de indice
 *
 * @author <a href="mailto:alfredo.furtado@pdcase.com.br">Alfredo Furtado</a>.
 * @version $Revision: 1.0 $
 */
public enum EsferaType {

	FEDERAL("Federal"), MUNICIPAL("Municipal"), ESTADUAL("Estadual");

	private String descricao;

	private EsferaType(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
