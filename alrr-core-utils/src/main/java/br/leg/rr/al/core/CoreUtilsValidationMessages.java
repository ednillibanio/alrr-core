package br.leg.rr.al.core;

import br.leg.rr.al.core.domain.StatusType;

/**
 * Lista dos resources que encontram no sistema.
 * 
 * @author libanioe
 * 
 */
public final class CoreUtilsValidationMessages {

	/**
	 * Informa que o novo registro foi salvo com sucesso.
	 */
	public static final String REGISTRO_SALVO_COM_SUCESSO = "Registro salvo com sucesso.";

	/**
	 * Informa que o registro foi reativado. Ou seja, deixou de ser inativo e passou
	 * para ativo.
	 * 
	 * @see {@link StatusType}
	 */
	public static final String REGISTRO_ATIVADO_COM_SUCESSO = "Registro ativado com sucesso.";

	/**
	 * Informa que o registro foi desativado. Ou seja, deixou de ser ativo e passou
	 * para inativo.
	 * 
	 * @see {@link StatusType}
	 */
	public static final String REGISTRO_DESATIVADO_COM_SUCESSO = "Registro desativado com sucesso.";
	/**
	 * Informa que o registro foi excluido com sucesso.
	 */
	public static final String REGISTRO_EXCLUIDO_COM_SUCESSO = "Registro excluído com sucesso.";

	/**
	 * Informa que o registro foi editado/atualizado com sucesso.
	 */
	public static final String REGISTRO_ATUALIZADO_COM_SUCESSO = "Registro atualizado com sucesso.";

	/**
	 * Informa que o registro renovado co sucesso.
	 */
	public static final String REGISTRO_RENOVADO_COM_SUCESSO = "Registro renovado com sucesso.";

	/**
	 * Informa que o registro já existe no banco de dados.
	 */
	public static final String REGISTRO_JA_EXISTE = "Registro já existe.";

	/**
	 * Informa que nenhuma entidade foi selecionada. Por exemplo, um dataTable com
	 * vários registros e o usuário clica em editar sem selecionar um registro. o
	 * sistema retorna uma mensagem informando que nenhum registro foi selecionado.
	 */
	public static final String REGISTRO_NAO_SELECIONADO = "Nenhum registro selecionado. Selecione um registro.";

	/**
	 * Informa que nenhum registro foi encontrado na pesquisa.
	 */
	public static final String REGISTRO_NAO_ENCONTRADO = "Nenhum registro foi encontrado.";

	/**
	 * Informa que nenhum filtro foi informado para realizar a pesquisa. É
	 * necessário preencher pelo menos um campo do filtro.
	 */
	public static final String FILTRO_NAO_INFORMADO = "Nenhum filtro foi informado. Informe um filtro pelo menos.";

	/**
	 * Pergunta se realmente deseja excluir registro.
	 */
	public static final String DESEJA_EXCLUIR_REGISTRO = "Deseja realmente excluir o registro?";

	/**
	 * Informa que a operação foi realizada com sucesso.
	 */
	public static final String OPERACAO_REALIZADA_COM_SUCESSO = "Operação realizada com sucesso.";

	/**
	 * Informa que o valor informado já existe.
	 */
	public static final String VALOR_JA_EXISTE = "O valor informado já existe. Informe outro valor.";

	/**
	 * Informa que o objeto ou registro selecionado, foi ativado com sucesso. Neste
	 * caso, a situação da entidade está 'ativa'.
	 */
	public static final String ATIVADO_COM_SUCESSO = "{0} ativado com sucesso.";

	/**
	 * Informa que o objeto ou registro selecionado, foi desativado com sucesso.
	 * Neste caso, a situação da entidade está 'inativa'.
	 */
	public static final String DESATIVADO_COM_SUCESSO = "{0} desativado com sucesso.";

	/**
	 * Informa que uma entidade foi vinculada a outra com sucesso.
	 * <code>param {0} - qualquer valor para exibir na mensagem.</code>
	 */
	public static final String ADICIONADO_COM_SUCESSO = "{0} adicionado com sucesso.";

	/**
	 * Informa que uma entidade foi desvinculada da outra com sucesso.
	 * <code>param {0} - qualquer valor para exibir na mensagem.</code>
	 */
	public static final String REMOVIDO_COM_SUCESSO = "{0} removido com sucesso.";

	/**
	 * Informa que a coluna no banco de dados não pode ser nulo.<br/>
	 * <code>param {0} - nome da entidade.</code><br/>
	 * <code>param {1} - campo com valor null.</code>
	 */
	public static final String DB_ERROR_NOT_NULL_CONSTRAINT = "Entidade {0}: Valor null na columa {1} viola a constraint not-null.";

	/**
	 * Informa que a entidade está <code>null</code>.<br/>
	 * <code>param {0} - nome da entidade.</code><br/>
	 */
	public static final String ENTIDADE_ESTA_NULL = "Entidade {0} está null.";

	/**
	 * Preenchimento obrigatório do campo Situação.
	 */
	public static final String INFORME_A_SITUACAO = "Preenchimento obrigatório do campo situação.";

	/**
	 * Informa que o email é inválido.
	 */
	public static final String EMAIL_INVALIDO = "Email inválido. Informe outro email.";

	/**
	 * Informa que o email é inválido.
	 */
	public static final String ERROR_503 = "Não foi possível executar operação. Servidor temporariamente indisponível. Tente mais tarde.";

}
