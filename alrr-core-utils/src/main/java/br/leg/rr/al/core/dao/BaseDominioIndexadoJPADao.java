package br.leg.rr.al.core.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

import br.leg.rr.al.core.domain.StatusType;
import br.leg.rr.al.core.jpa.DominioIndexado;

/**
 * Além desse classe herdar as caracteristicas da classe abstrata
 * BaseJPADaoStatus, ela implementa métodos básicos que manipulam entidades do
 * tipo DominioIndexado, como por exemplo, pesquisas pelo campo 'nome'.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * 
 * @since 1.0.0
 *
 * @param <T> Entidade que será manipulada
 */
public abstract class BaseDominioIndexadoJPADao<T extends DominioIndexado> extends BaseJPADaoStatus<T, Integer>
		implements DominioIndexadoJPADao<T> {

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
	public List<T> buscarAtivosPorNomeIndexado(String texto) {

		List<T> result = buscarPorNomeIndexado(texto);

		if (result != null && result.size() > 0) {
			result = result.stream().filter(entidade -> entidade.getSituacao().equals(StatusType.ATIVO))
					.collect(Collectors.toList());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.leg.rr.al.core.dao.DominioIndexadoIndexadoDaoStatus#buscarPeloNomeIndexado
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> buscarPorNomeIndexado(String texto) {
		EntityManager em = getEntityManager();
		List<T> result = null;

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(entityClass).get();

		Analyzer analyzer = fullTextEntityManager.getSearchFactory().getAnalyzer(DominioIndexado.NOME_ANALYZER);
		TokenStream token;
		String query = null;
		try {
			token = analyzer.tokenStream(null, texto);
			token.reset();
			CharTermAttribute term = token.getAttribute(CharTermAttribute.class);
			while (token.incrementToken()) {
				query = term.toString();
				break;
			}
			token.end();
			token.close();

			if (StringUtils.isNotBlank(query)) {
				org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields("nome").matching(texto)
						.createQuery();
				javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, entityClass);
				result = jpaQuery.getResultList();
			}

		} catch (IOException e) {
			logger.error(fatal, e.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.leg.rr.al.core.dao.DominioIndexadoIndexadoDaoStatus#buscarPeloNomeIndexado
	 * (java.lang.String, br.leg.rr.al.core.jpa.DominioIndexado)
	 */
	@Override
	public List<T> buscarPorNomeIndexado(String texto, List<T> excluidos) {

		List<T> result = buscarPorNomeIndexado(texto);

		if (result != null && result.size() > 0) {
			result = result.stream().filter(entidade -> entidade.getSituacao().equals(StatusType.ATIVO))
					.collect(Collectors.toList());
			if (excluidos != null && excluidos.size() > 0) {
				result.removeAll(excluidos);
			}
		}

		return result;
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

	/**
	 * Retorna uma lista de parametros usado na query.
	 * 
	 * @param query
	 * @return
	 */
	/*
	 * private String[] createParameterList(final String query) { final Matcher
	 * matcher = Pattern.compile(":[^\\s]*").matcher(query); List<String> paramList
	 * = new ArrayList<String>(); while (matcher.find()) { paramList
	 * .add(this.hql.substring(matcher.start() + 1, matcher.end())); }
	 * 
	 * return paramList.toArray(new String[paramList.size()]); }
	 * 
	 * private void setQueryParameters(Query query, String[] params) { Field field;
	 * for (int i = 0; i < params.length; i++) { try { field =
	 * entityClass.getDeclaredField(params[i]); field.setAccessible(true);
	 * 
	 * query.setParameter(params[i], (null != field.get(value)) ? field.get(value) :
	 * ""); } catch (final NoSuchFieldException e) { throw new
	 * SystemException(e.getMessage()); } catch (final IllegalAccessException e) {
	 * throw new SystemException(e.getMessage()); } } }
	 */
}
