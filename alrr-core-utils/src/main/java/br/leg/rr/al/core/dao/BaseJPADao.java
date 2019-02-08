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
import javax.persistence.criteria.Expression;
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

//TODO: COLOCAR O TRATAMENTO DE LANÇAR EXCEÇÕES no SISTEMA. PASSAR A EXCEÇÃO PARA SER TRATADO NA CAMADA DE VIEW. IMPORTANTISSIMO!!!
/**
 * Esta classe abstrata implementa os métodos básicos que são reutilizaveis por
 * seus sucessores. Ela implementa os conceitos do framework JPA para manipular
 * os dados das entidades.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 *
 * @param <T> Entidade a ser manipulada
 * @param <ID> Tipo da chave-primária ou identificador único.
 */
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

	public void excluir(Collection<T> entities) {
		for (T entity : entities) {
			try {
				excluir(entity);
			} catch (BeanException e) {
				logger.error(fatal, e.getMessage());
			}
		}
	}

	public void excluir(T entidade) throws PersistenceException {
		T entityToRemove = getEntityManager().merge(entidade);
		getEntityManager().remove(entityToRemove);

	}

	@SuppressWarnings("unchecked")
	protected List<T> buscarPorNamedQuery(String namedQuery) {
		try {
			return getEntityManager().createNamedQuery(namedQuery).getResultList();
		} catch (Exception e) {
			throw e;
		}

	}

	@SuppressWarnings("unchecked")
	protected List<T> buscarPorNamedQuery(String namedQuery, Map<String, Object> params) {
		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);
			QueryUtils.setQueryParameters(query, params);

			return query.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	protected List<T> buscarPorNamedQuery(String namedQuery, String param, Object value) {
		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);
			query.setParameter(param, value);
			return query.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param namedQuery
	 * @return
	 */
	// FIXME: tratar a exceção de quanto não possui nenhum registro encontrado.
	@SuppressWarnings("unchecked")
	protected T buscarEntidadePorNamedQuery(String namedQuery) {
		try {
			return (T) getEntityManager().createNamedQuery(namedQuery).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param namedQuery
	 * @param param
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T buscarEntidadePorNamedQuery(String namedQuery, String param, Object value) {
		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);
			query.setParameter(param, value);
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param namedQuery
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T buscarEntidadePorNamedQuery(String namedQuery, Map<String, Object> params) {
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

	@Override
	public T persistir(T entidade) throws BeanException {
		getEntityManager().persist(entidade);
		return entidade;
	}

	@Override
	public List<T> buscarPorNome(String nome) {

		CriteriaQuery<T> cq = getCriteriaBuilder().createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.select(root);
		Expression<String> exp = getCriteriaBuilder().lower(root.get("nome"));
		Predicate like = getCriteriaBuilder().like(exp, "%" + nome.toLowerCase().trim() + "%");

		cq.where(like);

		return getResultList(cq);
	}

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

	/**
	 * Retorna a instância do EntityManager que manipula a entidade.
	 * 
	 * @return EntityManager instância
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Retorna uma instância do CriteriaBuilder para a criação da CriteriaQuery.
	 * 
	 * @return CriteriaBuilder instância
	 */
	protected CriteriaBuilder getCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}

	/**
	 * Cria uma CriteriaQuery objeto com o tipo do resultado esperado.
	 * 
	 * @return objeto do criteria query
	 */
	protected CriteriaQuery<T> createCriteriaQuery() {
		return getEntityManager().getCriteriaBuilder().createQuery(entityClass);
	}

	/**
	 * Método de reutilização que executa uma query SELECT e retorna uma lista.
	 * 
	 * @param cq Query que foi montada para ser executada.
	 * @return Retorna uma lista de entidades encontradas a partir do CriteriaQuery
	 *         informada.
	 */
	protected List<T> getResultList(CriteriaQuery<T> cq) {
		TypedQuery<T> q = getEntityManager().createQuery(cq);
		return q.getResultList();
	}

	/**
	 * Método de reutilização que executa uma query SELECT e retorna um único
	 * objeto.
	 * 
	 * @param cq argumento usado para criar a query
	 * @return entidade encontrada ou null caso não encontrada. A exceção
	 *         NoResultException não é lançada neste cenário. Em vez disso é
	 *         retornado null.
	 */
	protected T getSingleResult(CriteriaQuery<T> cq) {
		try {
			TypedQuery<T> q = getEntityManager().createQuery(cq);
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw e;
		}

	}

}
