package br.leg.rr.al.core.dao;

import java.io.Serializable;

import br.leg.rr.al.core.domain.StatusType;
import br.leg.rr.al.core.jpa.Dominio;
import br.leg.rr.al.core.jpa.EntityStatus;

public interface DaoStatus<T extends EntityStatus<ID>, ID extends Serializable> extends Dao<T, ID> {

	/**
	 * Método usado para 'ativar' ou 'desativar' um domínio. <br/>
	 * Por exempo, quando um registro deixa de ser necessário, o usuário pode
	 * desativá-lo. Essa ação substitui a ação de excluir o registro. <br/>
	 * Além disso, o param <code>sit</code> é usado para validar se o domínio já não
	 * sofreu alteração na sua situação. Caso já tenha sido alterada, uma exceção
	 * será lançada.
	 * 
	 * @param sit
	 *            situação no qual deseja alterar o domínio.
	 * 
	 * @param dominio
	 *            domínio no qual será alterado a situação.
	 * 
	 * @return Domínio com a nova situação.
	 */
	public T mudarSituacao(StatusType sit, T dominio) throws BeanException;

	/**
	 * Método que altera a situação do domínio para <code>ativo</code>.
	 * 
	 * @param dominio
	 *            domínio que será modificada a situação.
	 * @return Domínio modificado com a situação igual a 'ativo'.
	 * @see StatusType
	 * @see #mudarSituacao(StatusType, Dominio)
	 */
	T ativar(T dominio) throws BeanException;

	/**
	 * Método que altera a situação do domínio para <cocde>inativo</code>.
	 * 
	 * @param dominio
	 *            domínio que será modificada a situação.
	 * @return Domínio modificado com a situação igual a 'inativo'.
	 * @see StatusType
	 * @see #mudarSituacao(StatusType, Dominio)
	 */
	T desativar(T dominio) throws BeanException;
}
