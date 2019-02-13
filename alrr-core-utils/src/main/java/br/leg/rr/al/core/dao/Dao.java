package br.leg.rr.al.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.PersistenceException;

import br.leg.rr.al.core.jpa.Entity;

/**
 * Esta interface define os métodos básicos de transação conhecidos como CRUD
 * (Create - salvar, Read - buscar, Update - atualizar e Delete - excluir). Além
 * dessas 4 operações, possui uma que serve para verificar se um objeto já
 * existe na base de dados.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * 
 * @param <T> classe do tipo entidade que irá realizar a manutenção dos seus
 *        dados.
 * @param <ID> tipo da chave primária (Long, Integer, etc)
 */
public interface Dao<T extends Entity<ID>, ID extends Serializable> extends Serializable {

	/**
	 * Salva uma entidade na base de dados. Se a entidade já existir, os dados serão
	 * atualizados.
	 * 
	 * @param entidade valor a ser salvo.
	 * @throws PersistenceException
	 */
	public void salvar(T entidade) throws PersistenceException;

	/**
	 * Salva varias entidades ao mesmo tempo na base de dados. Caso já existe
	 * alguma, será atualizada.
	 * 
	 * @param entidades valores a serem salvos.
	 */
	void salvar(Collection<T> entidades) throws PersistenceException;

	/**
	 * Recupera uma entidade a partir do seu identificador.
	 * 
	 * @param id identificador (chave-primária) usado para encontrar a entidade.
	 * @return entidade encontrada. Caso não encontre, retorna null.
	 */
	T buscar(ID id);

	/**
	 * Recupera uma entidade a partir da entidade informada.
	 * 
	 * @param entidade instância usada na busca.
	 * @return retorna a entidade. Caso contrário, retorna null.
	 */
	public T buscar(T entidade);

	/**
	 * Busca todas as entidades sem filtro.
	 * 
	 * @return T retorna uma lista de entidades. Caso não encontre, retorna null.
	 */
	public List<T> buscarTodos();

	/**
	 * Busca todos os registros pelo campo 'nome', caso existe o campo na entidade.
	 * O valor do param 'nome'será transformado para minusculo e pesquisado da
	 * seguinte maneira: %nome%.
	 * 
	 * @param nome campo de pesquisa. O campo 'nome' será pesquisado com a condição
	 *             <i>like</i>.
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
	 * @param entidade objeto a ser verificado se já existe.
	 * @return true se já existe. False caso contrário.
	 */
	public Boolean jaExiste(T entidade);

	/**
	 * Esse método deve ser chamado apenas para salvar mudançcas realizadas numa
	 * entidade que já existe. <br>
	 * O método atualiza as mudanças realizadas no objeto do atual contexto e salva
	 * as alterações no banco de dados.
	 * 
	 * @param entidade valor a ser atualizado
	 * @return entidade atualizada.
	 * @throws PersistenceException
	 */
	public T atualizar(T entidade) throws PersistenceException;

	/**
	 * Exclui uma entidade.
	 * 
	 * @param entidade entidade a ser exluída.
	 * @throws PersistenceException
	 */
	public void excluir(T entidade) throws PersistenceException;

	/**
	 * Exclui uma coleção de entidades.
	 * 
	 * @param entidades coleção de entidades a serem excluidas.
	 * 
	 */
	void excluir(Collection<T> entidades);

	/**
	 * Esse método descarta as mudanças feitas na entidade e retorna o objeto ao
	 * estado anterior. Uma nova busca na base de dados é realizada para obter os
	 * dados atualizados.
	 * 
	 * @param entidade entidade a ser renovada.
	 * @return retorna o objeto com os dados renovados.
	 */
	public void renovar(T entidade);

}
