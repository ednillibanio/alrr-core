package br.leg.rr.al.core.dao;

import java.util.List;

import br.leg.rr.al.core.jpa.DominioIndexado;

/**
 * Esta interface do tipo Dao deve ser implementado por classes que manipulam
 * entidades do tipo DominioIndexado.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * 
 * @since 1.0.0
 * 
 * @param <T> entidade que deve ser manipulada por esse Dao.
 * @see {@link DominioIndexado}
 */
public interface DominioIndexadoJPADao<T extends DominioIndexado> extends JPADaoStatus<T, Integer> {

	/**
	 * Busca todos os objetos que contém parte do nome informado.
	 * 
	 * @param nome valor a ser pesquisado.
	 * @return uma lista de entidades que satisfazem o valor do parametro nome.
	 * @throws BeanException
	 */
	@Override
	public List<T> buscarPorNome(String nome) throws BeanException;

	/**
	 * Busca todos os registros que a 'situacao' seja igual a
	 * {@code StatusType#ATIVO} pelo 'nome' informado.<br>
	 * Método util para carregar componentes de combo que o usuário pode
	 * selecioná-lo.<br>
	 * A cada caracter que o usuário informar/digitar, o sistema fará uma busca na
	 * lista.
	 * 
	 * @param nome campo que contém o valor a ser pesquisado no atributo
	 *             <code>nome</nome>.
	 * @return  Lista de entidades com a 'situacao' igual a {@code StatusType#ATIVO} e que
	 *         satisfaça a condição do parametro nome. Caso nenhum valor seja
	 *         encontrado, retorna <code>null</code>;
	 */
	List<T> getAtivosPorNome(String nome);

	/**
	 * <p>
	 * Busca todos os registros que a situação seja igual a
	 * {@code StatusType#ATIVO}, exceto os que foram informados no parametro
	 * 'excluidos'.
	 * <p>
	 * Método util para carregar componentes de select várias entidades que o
	 * usuário pode selecioná-la. A cada caracter que o usuário informar/digitar, o
	 * sistema fará uma busca na lista excluindo aqueles que fazem parte do
	 * parametro excluidos.
	 * </p>
	 * 
	 * @param nome      campo que contém o valor a ser pesquisado no atributo
	 *                  <code>nome</nome>.
	 * @param excluidos lista de entidades que não devem ser pesquisados ou
	 *                  retornados como resultado da busca.
	 * @return Lista de entidades que satisfaz a condição do parametro nome e que
	 *         não fazem parte da lista dos excluídos. Caso nenhum valor seja
	 *         encontrado, retorna {@code null};
	 * 
	 */
	List<T> getAtivosPorNome(String nome, List<T> excluidos);

	/**
	 * <p>
	 * Método que busca as entidades pelo nome informado como argumento. A diferença
	 * desse método para o {@link #buscarPorNome(String)} é que a busca é feita pelo
	 * indice que é criado pelo framework Lucene.
	 * </p>
	 * <p>
	 * Neste caso, a busca por palavras com ou sem acento são desconsideradas. Por
	 * exemplo, palavra que contém vogais com acento (ã, à, é, ê) mas que no
	 * argumento <code>query</code> não forem informadas, o método irá buscar do
	 * mesmo jeito palavras com vogais com acento. Se no argumento for informado
	 * 'nucleo' ou 'nuc', o sistema irá pesquisar também núc ou núcleo.
	 * </p>
	 * 
	 * @param texto texto a ser pesquisado
	 * @return lista de entidades com o nome que satisfaz o argumento query
	 *         informado.
	 */
	List<T> buscarAtivosPeloNomeIndexado(String texto);

	List<T> buscarPeloNomeIndexado(String texto);

	List<T> buscarPeloNomeIndexado(String texto, List<T> excluidos);

}
