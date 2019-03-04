package br.leg.rr.al.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.leg.rr.al.core.jpa.Entity;
import br.leg.rr.al.core.jpa.EntityStatus;

/**
 * Esta interface do tipo utils, define os métodos que podem ser utlizados por
 * classes que usam JPA e/ou EJB como tecnologia de manipulação dos dados.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * 
 * @param <T> Entidade que será manipulada.
 * @param <ID> Tipo da chave primária ou identificador único.
 * @see {@link JPADao}
 * @see {@link JPADaoStatus}
 */
public interface JPADaoUtils<T extends Entity<ID>, ID extends Serializable> extends Serializable {

	/**
	 * Filtro de busca usado no método {@link #pesquisar(java.util.Map)}.<br>
	 * Nem todas as classes possuem esse filtro. Consulte antes os atributos da
	 * entidade para ter certeza de usá-la.
	 * 
	 * @value {@literal String};
	 */
	String PESQUISAR_PARAM_NOME = "nome";

	/**
	 * Total (<i>count</i>) de registros encontrados na pesquisa ou numa lista de
	 * entidades.
	 * 
	 * @return retorna um inteiro com a quantidade total.
	 */
	int total();

	// TODO: JAVADOC.
	List<T> buscar(int maxResults, int firstResult);

	/**
	 * Realiza o FLUSH dos dados para o banco. Necessita de uma transação iniciada
	 * para funcionar.
	 * 
	 * 
	 * @throws ControllerException
	 */
	public void flush() throws BeanException;

	/**
	 * Torna a entidade off-line para trabalhar. Útil para realizar a transação de
	 * editar.
	 * 
	 * @param entidade valor a ser desatachado da persistencia.
	 */
	void detached(T entidade);

	/**
	 * Salva uma nova entidade. Se a entidade já estiver persistida, então deve
	 * chamar o método atualizar.
	 * 
	 * @param entidade entidade transient a ser salva.
	 * @return entidade transient persistida (salva).
	 */
	T persistir(T entidade) throws BeanException;

	/**
	 * Método que faz uma pesquisa com vários parametros. Os parametros são
	 * definidos de acordo com cada classe que implementa essa interface.
	 * 
	 * @param params lista de parametros.
	 * @return lista de entidades encontradas de acordo com os params informados, ou
	 *         null caso não encontrado.
	 */
	List<T> pesquisar(Map<String, Object> params);

	/**
	 * <p>
	 * Método que carrega (fetch) a entidade principal e suas entidades dependentes.
	 * Esse método é um método secundário utilizado por vários outros métodos
	 * primários.
	 * </p>
	 * <p>
	 * A diferença desse método para o {@link DaoStatus#buscar(EntityStatus)} é que
	 * esse método carrega as entidades dependentes e o outro não.
	 * <p>
	 * Método muito utilizado pelos métodos de controle de view. Por exemplo, para
	 * editar usuário, é necessário carregar suas permissões, seus sistemas e dados
	 * de pessoa. Nesse caso, toda vez que o usuário clicar no botão editar, o
	 * sistema deve carregar todos os dados de usuário para edição.
	 * </p>
	 * 
	 * @param entidade deve possuir o id para busca.
	 * @return entidade carregada com as suas dependencias.
	 * @see JPADaoUtils#renovar(EntityStatus)
	 */
	T carregar(T entidade);

}
