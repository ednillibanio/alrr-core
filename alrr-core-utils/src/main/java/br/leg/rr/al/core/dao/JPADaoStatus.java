package br.leg.rr.al.core.dao;

import java.io.Serializable;

import br.leg.rr.al.core.domain.StatusType;
import br.leg.rr.al.core.jpa.EntityStatus;

public interface JPADaoStatus<T extends EntityStatus<ID>, ID extends Serializable>
		extends JPADaoUtils<T, ID>, DaoStatus<T, ID> {

	/**
	 * Busca entidade pelo campo "situacao".<br>
	 *
	 * @value {@link StatusType}
	 */
	public static final String PESQUISAR_PARAM_SITUACAO = "situacao";

}
