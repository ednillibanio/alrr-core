package br.leg.rr.al.core.dao;

import java.io.Serializable;

import br.leg.rr.al.core.domain.StatusType;
import br.leg.rr.al.core.jpa.EntityStatus;

/**
 * Esta interface representa a união de duas outras interfaces: JPADaoUtils e
 * DaoStatus. Classes do tipo JPADaoStatus manipulam entidades do tipo
 * EntityStatus.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * 
 * @param <T> Entidade que será manipulada.
 * @param <ID> Tipo da chave primária ou identificador único.
 */
public interface JPADaoStatus<T extends EntityStatus<ID>, ID extends Serializable>
		extends JPADaoUtils<T, ID>, DaoStatus<T, ID> {

	/**
	 * Filtro de busca usado no método {@link #pesquisar(java.util.Map)}.<br>
	 *
	 * @value {@link StatusType}
	 */
	public static final String PESQUISAR_PARAM_SITUACAO = "situacao";

}
