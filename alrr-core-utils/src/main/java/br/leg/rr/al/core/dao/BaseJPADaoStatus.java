package br.leg.rr.al.core.dao;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.leg.rr.al.core.domain.StatusType;
import br.leg.rr.al.core.jpa.EntityStatus;

//TODO: COLOCAR O TRATAMENTO DE LANÇAR EXCEÇÕES no SISTEMA. PASSAR A EXCEÇÃO PARA SER TRATADO NA CAMADA DE VIEW. IMPORTANTISSIMO!!!
public abstract class BaseJPADaoStatus<T extends EntityStatus<ID>, ID extends Serializable> extends BaseJPADao<T, ID>
		implements JPADaoStatus<T, ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8054203563918294954L;

	private final Logger logger = LoggerFactory.getLogger(BaseJPADaoStatus.class);

	@Override
	public T mudarSituacao(StatusType sit, T entidade) throws BeanException {
		T temp = buscar(entidade);
		// lança exceção de que já foi modificado a situação da entidade.
		if (temp.getSituacao().equals(sit)) {
			throw new BeanException(
					"Não foi possível alterar a situação. Situação já foi alterada em outra transação.");
		}
		temp.setSituacao(sit);
		salvar(temp);

		return temp;
	}

	@Override
	public T ativar(T dominio) throws BeanException {
		return mudarSituacao(StatusType.ATIVO, dominio);
	}

	@Override
	public T desativar(T dominio) throws BeanException {
		return mudarSituacao(StatusType.INATIVO, dominio);
	}

}
