package br.leg.rr.al.core.dao;

import java.util.List;

import br.leg.rr.al.core.jpa.Dominio;

/**
 * Esta interface do tipo Dao deve ser implementado por classes que manipulam
 * entidades do tipo Dominio.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * 
 * @since 1.0.0
 * 
 * @param <T> entidade que deve ser manipulada por esse Dao.
 * @see {@link Dominio}
 */
public interface DominioDao<T extends Dominio> extends JPADaoStatus<T, Integer> {

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

}
