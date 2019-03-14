package br.leg.rr.al.core.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.leg.rr.al.core.domain.StatusType;
import br.leg.rr.al.core.jpa.Dominio;

/**
 * Além desse classe herdar as caracteristicas da classe abstrata
 * BaseJPADaoStatus, ela implementa métodos básicos que manipulam entidades do
 * tipo Dominio, como por exemplo, pesquisas pelo campo 'nome'.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * 
 * @since 1.0.0
 *
 * @param <T> Entidade que será manipulada
 */
public abstract class BaseDominioJPADao<T extends Dominio> extends BaseJPADaoStatus<T, Integer>
		implements DominioJPADao<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8054203563918294954L;

	@Override
	public List<T> buscarAtivosPorNome(String nome) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PESQUISAR_PARAM_NOME, StatusType.ATIVO);
		params.put(PESQUISAR_PARAM_NOME, nome);
		return pesquisar(params);

	}

	@Override
	public List<T> buscarAtivosPorNome(String nome, List<T> excluidos) {
		List<T> resultado = buscarAtivosPorNome(nome);
		if (excluidos != null && !resultado.isEmpty()) {
			resultado.removeAll(excluidos);
		}
		return resultado;
	}

	@Override
	public List<T> pesquisar(Map<String, Object> params) {

		String nome = null;
		StatusType situacao = null;
		List<Predicate> predicates = new ArrayList<Predicate>();

		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<T> cq = getCriteriaBuilder().createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.select(root);

		if (params.size() > 0) {

			if (params.containsKey(PESQUISAR_PARAM_NOME)) {
				nome = (String) params.get(PESQUISAR_PARAM_NOME);
				if (StringUtils.isNotBlank(nome)) {
					Predicate cond = cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
					predicates.add(cond);
				}
			}

			if (params.containsKey(PESQUISAR_PARAM_SITUACAO)) {
				situacao = (StatusType) params.get(PESQUISAR_PARAM_SITUACAO);
				if (situacao != null) {
					Predicate cond = cb.equal(root.get("situacao"), situacao);
					predicates.add(cond);
				}
			}

			cq.where(cb.and(predicates.toArray(new Predicate[] {})));
			return getResultList(cq);
		}

		return null;

	}
}
