package br.leg.rr.al.core.jpa;

import java.io.Serializable;

import br.leg.rr.al.core.domain.StatusType;

/**
 * <p>
 * Está interface, além de ser do tipo {@code Entity} e ter um identificador
 * único, ela possui um <code>status</code> (situacao). Este campo serve para
 * definir se a entidade está 'ativa' ou 'inativa'. Por exemplo, uma entidade
 * que não pode mais ser vinculada a outra entidade ou ser carregada num
 * componente de seleção, mas deve-se mante-la na base de dados porque existem
 * vários outras entidades vinculadas a essa entidade e que não podem ser
 * apagados para não ficarem inconsistentes. Neste caso, a situação da entidade
 * passa a ser 'inativa'. Num caso mais real, podemos citar um perfil de usuário
 * que momentaneamente não poderá mais ser selecionado e que não pode ser
 * apagado porque em breve será liberado para ser usado novamente.
 * </p>
 * <p>
 * Essa interface é muito utilizada por classes de dominios. Como EstadoCivil,
 * Bairros, Municipios, Profissão, entre outros. Exemplo:
 * </p>
 * 
 * @see {@link BaseEntityStatus}
 * 
 *      <pre>
 * <code>
 * public class Profissao implements EntityStatus{@literal <Long>} {
 * ...
 * }
 * </code>
 *      </pre>
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * @param <ID> define o tipo do identificador. Por exemplo, pode ser Integer,
 *        Long, etc.
 */
public interface EntityStatus<ID extends Serializable> extends Entity<ID> {

	/**
	 * Retorna a situação da entidade.
	 * 
	 * @return situação da entidade
	 * @see {{@link StatusType}
	 */
	public StatusType getSituacao();

	/**
	 * Define a situação da entidade.
	 * 
	 * @param sit situação da entidade
	 * @see {{@link StatusType}
	 */
	public void setSituacao(StatusType sit);

}
