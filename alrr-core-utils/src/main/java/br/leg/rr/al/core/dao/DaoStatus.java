package br.leg.rr.al.core.dao;

import java.io.Serializable;
import java.util.List;

import br.leg.rr.al.core.domain.StatusType;
import br.leg.rr.al.core.jpa.Dominio;
import br.leg.rr.al.core.jpa.DominioIndexado;
import br.leg.rr.al.core.jpa.EntityStatus;

/**
 * Esta interface deve ser implementado por Dao's que manipulam entidades que
 * possuem o campo 'situacao'. Entidades do tipo {@code Dominio} possuem essa
 * característica.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * 
 * @since 1.0.0
 * 
 * @param <T> entidade que deve ser manipulada por esse Dao.
 * @param <ID> tipo da chave-primária ou identificador único da entidade.
 * @see {@link DominioIndexado}, {@link Dominio}, {@link DominioIndexadoJPADao}
 */
public interface DaoStatus<T extends EntityStatus<ID>, ID extends Serializable> extends Dao<T, ID> {

	/**
	 * <p>
	 * Método que altera a situação da entidade. Caso já tenha sido alterada, uma
	 * exceção será lançada. A verificação é realizada a partir do param 'sit'.
	 * </p>
	 * 
	 * @param sit      situação na qual deseja alterar a entidade.
	 * 
	 * @param entidade entidade na qual será alterada a situação.
	 * 
	 * @return Entidade com a nova situação.
	 */
	public T mudarSituacao(StatusType sit, T entidade) throws BeanException;

	/**
	 * Método que altera a situação da entidade para {@code StatusType#ATIVO}.
	 * 
	 * @param entidade objeto que será modificada a situação.
	 * @return Entidade que foi modificada na base de dados com a situação igual a
	 *         {@code StatusType#ATIVO}.
	 * @see StatusType
	 * @see #mudarSituacao(StatusType, EntityStatus)
	 */
	T ativar(T entidade) throws BeanException;

	/**
	 * Método que altera a situação da entidade para {@code StatusType#INATIVO}.
	 * 
	 * @param entidade objeto que será modificada a situação.
	 * @return Entidade que foi modificada na base de dados com a situação igual a
	 *         {@code StatusType#INATIVO}.
	 * @see StatusType
	 * @see #mudarSituacao(StatusType, EntityStatus)
	 */
	T desativar(T entidade) throws BeanException;

	/**
	 * Busca todos as entidades que a situação seja igual a
	 * {@code StatusType#INATIVO}.
	 * 
	 * @return Lista de entidades com a {@code situacao} igual a
	 *         {@code StatusType#INATIVO}. Caso nenhuma entidade seja encontrada,
	 *         retorna <code>null</code>;
	 */
	public List<T> getInativos();

	/**
	 * Busca todos os registros que a situação seja igual a
	 * {@code StatusType#ATIVO}.<br>
	 * Método util para carregar componentes de combo que o usuário pode
	 * seleciona-lo.
	 * 
	 * @return Lista de entidades do tipo <code>{@link Dominio} </code> ativos. Caso
	 *         nenhum valor seja encontra, retorna <code>null</code>;
	 */
	public List<T> getAtivos();

	/**
	 * Busca todos os registros que a situação seja igual a
	 * {@code StatusType#ATIVO}, exceto os que foram informados no @param excluidos.
	 * 
	 * @param excluidos lista de entidades que não devem ser pesquisados ou
	 *                  retornados como resultado da busca.
	 * @return lista de entidades com a situação igual a {@code StatusType#ATIVO}, e
	 *         que não fazem parte da lista dos 'excluidos'. Caso nenhum valor seja
	 *         encontrado, retorna <code>null</code>;
	 */
	public List<T> getAtivos(List<T> excluidos);

	/**
	 * <p>
	 * Busca todos os registros que a situação seja igual a
	 * {@code StatusType#ATIVO}, e acrescenta o registro que será carregado mesmo se
	 * não estiver com a situação ativo. Esse método é util quando não se tem
	 * certeza de que um registro está com a situação ativa, mas precisa carregá-lo
	 * mesmo assim.
	 * </p>
	 * <p>
	 * Por exemplo, editar uma entidade 'Objeto' que possui um 'Modulo' vinculado.
	 * Quando exibir o dialog de edição, a entidade Objeto precisa carregar todos os
	 * registros do tipo Modulo (numa dropdown) que estejam ativo. Porém não se sabe
	 * se o modulo vinculado ao objeto ainda está ativo. Para garantir que este
	 * registro seja carregado, passe-o como parametro a este método.
	 * </p>
	 * 
	 * @param entidade valor a ser incluído da lista de entidades ativas.
	 * @return lista de entidades ativas, mais a entidade passada como parâmetro.
	 *         Caso contrário, retorna null.
	 */
	public List<T> getAtivos(T entidade);

	/**
	 * Busca todas as entidades de acordo com a situação informada.
	 * 
	 * @param situacao filtro que define as entidades a serem buscadas
	 * @return lista de entidades que satisfaz a condição da 'situacao' informada.
	 */
	List<T> buscarPorSituacao(StatusType situacao);

}
