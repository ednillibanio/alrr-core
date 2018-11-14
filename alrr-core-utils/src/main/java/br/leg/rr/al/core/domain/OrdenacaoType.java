/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
/**
 * @(#) TipoOrdenacao.java.
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
 * Tipos de Ordenacao
 *
 * @author <a href="mailto:alfredo.furtado@pdcase.com.br">Alfredo Furtado</a>.
 * @version $Revision: 1.0 $
 */
public enum OrdenacaoType {

	C("Cronológica"), V("Valor"), P("Preferência");

	private String descricao;

	private OrdenacaoType(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
