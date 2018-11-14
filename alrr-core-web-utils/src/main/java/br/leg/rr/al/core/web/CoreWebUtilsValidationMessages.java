package br.leg.rr.al.core.web;

/**
 * Lista dos resources que encontram no sistema.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a><br/>
 *         Data Criação: 31-08-2018<br/>
 * @since 1.0.0
 * 
 */
public final class CoreWebUtilsValidationMessages {

	/**
	 * Preenchimento obrigatório do campo Situação.
	 */
	public static final String INFORME_A_SITUACAO = "Preenchimento obrigatório do campo situação.";

	/**
	 * Informa que pelo menos um filtro ou campo da pesquisa deve ser preenchido.
	 */
	public static final String INFORME_PELO_MENOS_UM_FILTRO = "Informe pelo menos um filtro.";

	/**
	 * Informa que a data de expedição do documento é maior que hoje.
	 */
	public static final String DATA_MAIOR_QUE_HOJE = "Data invalida. Data não pode ser maior que a data de hoje.";

}
