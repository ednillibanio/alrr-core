package br.leg.rr.al.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.NotImplementedException;
import org.jodah.typetools.TypeResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import br.leg.rr.al.core.jpa.Entity;
import br.leg.rr.al.core.jpa.EntityStatus;

//TODO: COLOCAR O TRATAMENTO DE LANÇAR EXCEÇÕES no SISTEMA. PASSAR A EXCEÇÃO PARA SER TRATADO NA CAMADA DE VIEW. IMPORTANTISSIMO!!!
public abstract class BaseJPADao<T extends Entity<ID>, ID extends Serializable> implements JPADao<T, ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7225343950193770610L;

	protected static Marker fatal = MarkerFactory.getMarker("FATAL");
	private final Logger logger = LoggerFactory.getLogger(BaseJPADao.class);

	@PersistenceContext
	private EntityManager entityManager;

	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public BaseJPADao() {
		Class<?>[] typeArguments = TypeResolver.resolveRawArguments(BaseJPADao.class, getClass());
		this.entityClass = (Class<T>) typeArguments[0];

		// FIXME: Implementação acima corrige erro de herança no EJB. Está em fase de
		// teste. A implementação não aceita herança de vários níveis.
		/*
		 * Type c = getClass().getGenericSuperclass(); Type type = ((ParameterizedType)
		 * c).getActualTypeArguments()[0]; if
		 * (Class.class.isAssignableFrom(type.getClass())) { this.entityClass =
		 * (Class<T>) type; } else { this.entityClass = (Class<T>) ((ParameterizedType)
		 * type).getRawType(); }
		 */

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int total() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		Query q = getEntityManager().createQuery(cq);
		return ((Integer) q.getSingleResult()).intValue();
	}

	@Override
	public void excluir(Collection<T> entities) {
		for (T entity : entities) {
			try {
				excluir(entity);
			} catch (BeanException e) {
				logger.error(fatal, e.getMessage());
			}
		}
	}

	@Override
	public void excluir(T entidade) throws PersistenceException {
		T entityToRemove = getEntityManager().merge(entidade);
		getEntityManager().remove(entityToRemove);

	}

	@SuppressWarnings("unchecked")
	protected List<T> buscarPorNamedQuery(String namedQuery) {
		try {
			return getEntityManager().createNamedQuery(namedQuery).getResultList();
		} catch (Exception e) {
			logger.error(fatal, e.getMessage());
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	protected List<T> buscarPorNamedQuery(String namedQuery, EntityStatus<ID> params) {
		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);

			// TODO: antes estava do jeito que encontra comentado abaixo. Mas
			// não sei se funciona. Achei uma outra maneira que faz mais sentido
			// e coloquei no lugar.
			// QueryUtils.setQueryParameters(query, params);
			return query.getResultList();
		} catch (Exception ex) {
			logger.error(fatal, ex.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected List<T> buscarPorNamedQuery(String namedQuery, Map<String, Object> params) {
		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);
			QueryUtils.setQueryParameters(query, params);

			return query.getResultList();
		} catch (Exception ex) {
			logger.error(fatal, ex.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected List<T> buscarPorNamedQuery(String namedQuery, String param, Object value) {
		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);
			query.setParameter(param, value);
			return query.getResultList();
		} catch (Exception ex) {
			logger.error(fatal, ex.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @param namedQuery
	 * @return
	 */
	// FIXME: tratar a exceção de quanto não possui nenhum registro encontrado.
	@SuppressWarnings("unchecked")
	protected T buscarEntidade(String namedQuery) {
		try {
			return (T) getEntityManager().createNamedQuery(namedQuery).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			logger.error(fatal, e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @param namedQuery
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T buscarEntidade(String namedQuery, EntityStatus<ID> params) {
		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);
			QueryUtils.setQueryParameters(query, params);
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			logger.error(fatal, e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @param namedQuery
	 * @param param
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T buscarEntidade(String namedQuery, String param, Object value) {
		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);
			query.setParameter(param, value);
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			logger.error(fatal, e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @param namedQuery
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T buscarEntidade(String namedQuery, Map<String, Object> params) {
		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);
			QueryUtils.setQueryParameters(query, params);
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			logger.error(fatal, e.getMessage());
		}
		return null;
	}

	@Override
	public void flush() throws PersistenceException {
		getEntityManager().flush();
	}

	@Override
	public void renovar(T entidade) {
		if (getEntityManager().contains(entidade)) {
			getEntityManager().refresh(entidade);
		}
	}

	@Override
	public T buscar(ID id) {
		return getEntityManager().find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T buscar(T entidade) {
		return (T) getEntityManager().find(entidade.getClass(), entidade.getId());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<T> buscar(int maxResults, int firstResult) {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(maxResults);
		q.setFirstResult(firstResult);
		return q.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> buscarTodos() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getResultList(cq);
	}

	@Override
	public List<T> pesquisar(Map<String, Object> params) {
		throw new NotImplementedException("Método pesquisar não implementado.");
	}

	@Override
	public void salvar(Collection<T> entites) throws PersistenceException {
		for (T entity : entites) {
			salvar(entity);
		}
	}

	@Override
	public void salvar(T entidade) throws PersistenceException {

		if (entidade.getId() != null) {
			atualizar(entidade);
		} else {
			persistir(entidade);
		}

	}

	@Override
	public T atualizar(T entity) throws PersistenceException {
		entity = getEntityManager().merge(entity);
		// getEntityManager().flush();
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.leg.rr.al.core.dao.JPADaoStatus#persistir(br.leg.rr.al.core.jpa.
	 * EntityStatus)
	 */
	@Override
	public T persistir(T entidade) throws BeanException {
		getEntityManager().persist(entidade);
		return entidade;
	}

	@Override
	public List<T> buscarPorNome(String nome) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<T> cq = getCriteriaBuilder().createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.select(root);

		Predicate cond1 = cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase().trim() + "%");

		cq.where(cond1);
		TypedQuery<T> q = getEntityManager().createQuery(cq);
		return q.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.leg.rr.al.core.dao.JPADaoStatus#carregarEntidade(br.leg.rr.al.core.jpa.
	 * EntityStatus)
	 */
	@Override
	public T carregar(T entidade) {
		return buscar(entidade);
	}

	@Override
	public void detached(T entity) {
		getEntityManager().detach(entity);
	}

	protected String addOrderByHql(Order... orders) {
		String result = "";
		if (orders.length > 0) {
			StringBuilder builder = new StringBuilder(" order by ");
			for (int i = 0; i < orders.length - 1; i++) {
				builder.append(orders[i].toString());
				builder.append(", ");
			}
			builder.append(orders[orders.length - 1]);
			result = builder.toString();
		}

		return result;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected CriteriaBuilder getCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}

	protected CriteriaQuery<T> createCriteriaQuery() {
		return getEntityManager().getCriteriaBuilder().createQuery(entityClass);
	}

	/**
	 * Monta a query a partir do EntityManager, faz a busca e retorna as entidades
	 * encontradas.
	 * 
	 * @param cq
	 *            Query que foi montada para ser executada.
	 * @return Retorna uma lista de entidades encontradas a partir do CriteriaQuery
	 *         informada.
	 */
	protected List<T> getResultList(CriteriaQuery<T> cq) {
		TypedQuery<T> q = getEntityManager().createQuery(cq);
		return q.getResultList();
	}

	protected T getSingleResult(CriteriaQuery<T> cq) {
		try {
			TypedQuery<T> q = getEntityManager().createQuery(cq);
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			logger.error(fatal, e.getMessage());
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
