package br.leg.rr.al.core.dao;

import java.util.List;

import br.leg.rr.al.core.domain.StatusType;
import br.leg.rr.al.core.jpa.Dominio;

/**
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * @param <T>
 */
public interface DominioDaoStatus<T extends Dominio> extends JPADaoStatus<T, Integer> {

	/**
	 * Busca entidade pelo campo "nome".<br>
	 * 
	 * @value {@literal String};
	 */
	public static final String PESQUISAR_PARAM_NOME = "nome";

	/**
	 * Busca todos os registros que a situação seja <code>inativos</code>. <br>
	 * Dominio.situacao= StatusType.INATIVO;
	 * 
	 * @return Lista de entidades do tipo <code>{@link Dominio} </code> inativos.
	 *         Caso nenhum valor seja encontra, retorna <code>null</code>;
	 */
	public List<T> getInativos();

	/**
	 * Busca todos os registros que a situação seja <code>ativa</code>. Método util
	 * para carregar componentes de combo que o usuário pode seleciona-lo. <br>
	 * Dominio.situacao= StatusType.ATIVO;
	 * 
	 * @return Lista de entidades do tipo <code>{@link Dominio} </code> ativos. Caso
	 *         nenhum valor seja encontra, retorna <code>null</code>;
	 */
	public List<T> getAtivos();

	/**
	 * Busca todos os registros que a situação seja <code>ativa</code> e pelo nome
	 * informado. Método util para carregar componentes de combo que o usuário pode
	 * selecioná-lo. A cada caracter que o usuário informar/digitar, o sistema fará
	 * uma busca na lista. <br>
	 * Dominio.situacao= StatusType.ATIVO;
	 * 
	 * @param nome
	 *            campo que contém o valor a ser pesquisado no atributo
	 *            <code>nome</nome>.
	 * @return  Lista de entidades do tipo <code>{@link Dominio} </code> ativos e
	 *         que satisfaça a condição do @param nome. Caso nenhum valor seja
	 *         encontra, retorna <code>null</code>;
	 */
	List<T> getAtivosPorNome(String nome);

	/**
	 * @param situacao
	 * @return
	 */
	List<T> buscarPorSituacao(StatusType situacao);

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
	 * @param texto
	 *            texto a ser pesquisado
	 * @return lista de entidades com o nome que satisfaz o argumento query
	 *         informado.
	 */
	List<T> buscarAtivosPeloNomeIndexado(String texto);

	// TODO criar o comentário.
	@Override
	public List<T> buscarPorNome(String nome);

	List<T> buscarPeloNomeIndexado(String texto);

	List<T> buscarPeloNomeIndexado(String texto, List<T> excluidos);

}
