package br.leg.rr.al.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.leg.rr.al.core.jpa.Entity;
import br.leg.rr.al.core.jpa.EntityStatus;

public interface JPADao<T extends Entity<ID>, ID extends Serializable> extends JPADaoUtils<T, ID>, Dao<T, ID> {

	/**
	 * Total (<i>count</i>) de registros encontrados na pesquisa ou numa lista de
	 * entidades.
	 * 
	 * @return retorna um inteiro com a quantidade total.
	 */
	int total();

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
	 * @param entity
	 */
	void detached(T entity);

	/**
	 * Salva uma nova entidade. Se a entidade já estiver persistida, então deve
	 * chamar o método atualizar.
	 * 
	 * @param entidade
	 *            entidade transient a ser salva.
	 * @return entidade transient persistida (salva).
	 */
	T persistir(T entidade) throws BeanException;

	/**
	 * TODO: colocar o comentário do método. Ver se vai ficar mesmo aqui ou no
	 * DaoStatus. ou sei lá onde.
	 * 
	 * @param params
	 * @return
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
	 * esse carrega as entidades dependente e o outro não.
	 * <p>
	 * Método muito utilizado pelos métodos de controle de view. Por exemplo, para
	 * editar usuário, é necessário carregar suas permissões, seus sistemas e dados
	 * de pessoa. Nesse caso, toda vez que o usuário clicar no botão editar, o
	 * sistema deve carregar todos os dados de usuário para edição.
	 * </p>
	 * 
	 * @param entidade
	 *            entidade deve possuir o id para busca.
	 * @return entidade carregada com as suas dependentes.
	 * @see JPADao#renovar(EntityStatus)
	 */
	T carregar(T entidade);

}
