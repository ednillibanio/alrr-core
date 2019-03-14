package br.leg.rr.al.core.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.leg.rr.al.core.domain.StatusType;
import br.leg.rr.al.core.jpa.EntityStatus;

//TODO: COLOCAR O TRATAMENTO DE LANÇAR EXCEÇÕES no SISTEMA. PASSAR A EXCEÇÃO PARA SER TRATADO NA CAMADA DE VIEW. IMPORTANTISSIMO!!!
/**
 * Além desta classe abstrata herdar as características da classe abstrata
 * BaseJPADao, ela implementa métodos para tratar entidade do tipo EntityStatus.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * 
 * @since 1.0.0
 *
 * @param <T> Entidade a ser manipulada
 * @param <ID> Tipo da chave-primária ou identificador único.
 */
public abstract class BaseJPADaoStatus<T extends EntityStatus<ID>, ID extends Serializable> extends BaseJPADao<T, ID>
		implements JPADaoStatus<T, ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8054203563918294954L;

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

	@Override
	public List<T> getInativos() {
		return buscarPorSituacao(StatusType.INATIVO);
	}

	@Override
	public List<T> getAtivos() {
		return buscarPorSituacao(StatusType.ATIVO);

	}

	@Override
	public List<T> getAtivos(List<T> excluidos) {
		List<T> resultado = getAtivos();
		if (excluidos != null && !resultado.isEmpty()) {
			resultado.removeAll(excluidos);
		}
		return resultado;
	}

	@Override
	public List<T> getAtivos(T entidade) {
		List<T> resultado = getAtivos();

		if (resultado != null && !resultado.isEmpty()) {
			if (!resultado.contains(entidade)) {
				resultado.add(entidade);
			}
		}
		return resultado;
	}

	@Override
	public List<T> buscarPorSituacao(StatusType situacao) {

		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<T> cq = getCriteriaBuilder().createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.select(root);

		Predicate cond = cb.equal(root.get("situacao"), situacao);
		cq.where(cond);

		return getResultList(cq);
	}

}
