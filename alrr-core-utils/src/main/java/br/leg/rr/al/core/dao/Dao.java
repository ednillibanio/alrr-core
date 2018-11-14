package br.leg.rr.al.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.PersistenceException;

import br.leg.rr.al.core.jpa.Entity;

public interface Dao<T extends Entity<ID>, ID extends Serializable> extends Serializable {

	/**
	 * Salva ou persiste uma nova entidade. Se a entidade já existir (estiver
	 * persistida), então será chamado o método atualizar.
	 * 
	 * @param entidade
	 * @return
	 * @throws ControllerException
	 */
	public void salvar(T entidade) throws PersistenceException;

	/**
	 * @param entidades
	 */
	void salvar(Collection<T> entidades) throws PersistenceException;

	/**
	 * Recupera uma entidade a partir do id. A <code>entity</code> informado deve
	 * conter o ID a ser recuperado.
	 * 
	 * @param id
	 *            valor usado para encontrar a entidade.
	 * @return retorna a entidade encontrada. Caso não encontre, retorna null.
	 */
	T buscar(ID id);

	/**
	 * Recupera uma entidade a partir <code>entity</code> informado.
	 * 
	 * @param entidade
	 *            instancia usada na consulta.
	 * @return retorna a entidade. Caso contrário, retorna null.
	 */
	public T buscar(T entidade);

	/**
	 * Busca todas as entidades.
	 * 
	 * @return T retorna uma lista de entidades. Caso não encontre, retorna null.
	 */
	public List<T> buscarTodos();

	/**
	 * Busca todos os registros pelo campo 'nome', caso existe o campo na entidade.
	 * 
	 * @param nome
	 *            campo de pesquisa. O campo 'nome' será pesquisado com a condição
	 *            <i>like</i>.
	 * @return retorna uma lista de entidades conforme o 'nome' informado na
	 *         pesquisa.
	 */
	List<T> buscarPorNome(String nome);

	/**
	 * Verifica se uma entidade já existe na base de dados. Antecipa a validação da
	 * constraint do banco de dados para evitar o lançamento da exceção. Mesmo
	 * usando esse método, é obrigado criar as constraints que restringe a criação
	 * do mesmo registro por dois usuários diferentes.
	 * 
	 * @param entidade
	 * @return
	 */
	public Boolean jaExiste(T entidade);

	/**
	 * Esse método deve ser chamado apenas para salvar mudançcas realizadas numa
	 * entidade que já existe. <br>
	 * O método atualiza as mudanças realizadas no objeto desatachado do atual
	 * contexto persistente e salva as alterações no banco de dados.
	 * 
	 * @param entidade
	 *            entidade a ser atualizado
	 * @return entidade atualizado.
	 * @throws PersistenceException
	 */
	public T atualizar(T entidade) throws PersistenceException;

	/**
	 * Exclui uma entidade.
	 * 
	 * @param entidade
	 *            entidade a ser exluída.
	 * @throws ControllerException
	 */
	public void excluir(T entidade) throws PersistenceException;

	/**
	 * Exclui uma coleção de entidades.
	 * 
	 * @param entidades
	 *            coleção de entidades.
	 * 
	 */
	void excluir(Collection<T> entidades);

	/**
	 * Descarta as mudanças feitas na entidade, e retorna o objeto ao estado
	 * anterior.
	 * 
	 * @param entidade
	 *            entidade a ser recuperada.
	 * @return retorna o objeto com os dados originais.
	 */
	public void renovar(T entidade);

}
