package br.leg.rr.al.core.jpa;

import java.io.Serializable;

/**
 * <p>
 * Está interface deve ser implementada por classe que será uma entidade com um
 * identificador (id) único. Exemplo:
 * </p>
 * 
 * <pre>
 * <code>
 * public class Pessoa implements Entity{@literal <Long>} {
 * ...
 * }
 * </code>
 * </pre>
 * 
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * @see {@link BaseEntity}, {@link EntityStatus}
 * @param <ID> define o tipo do identificador. Por exemplo, pode ser Integer,
 *        Long, etc.
 */
public interface Entity<ID extends Serializable> extends Serializable {

	/**
	 * Retorna o identificador.
	 * 
	 * @return identificador da entidade
	 */
	public ID getId();

	/**
	 * Define o identificador.
	 * 
	 * @param id identificador da entidade
	 */
	public void setId(ID id);

}
