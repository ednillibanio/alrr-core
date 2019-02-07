package br.leg.rr.al.core.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import br.leg.rr.al.core.CoreUtilsValidationMessages;
import br.leg.rr.al.core.domain.StatusConverter;
import br.leg.rr.al.core.domain.StatusType;

/**
 * <p>
 * Está classe herda as características da classe {@code BaseEntity<ID>} e
 * implementa o campo <code>situação</code> da interface
 * {@link EntityStatus<ID>}. Além disso, é criado um atributo chamado 'version'
 * que serve de controle de versão da classe instânciada em cada sessão. Esse
 * campo evita que dados antigos sobreponham dados mais recentes.
 * <p>
 * <p>
 * Os métodos {@link #hashCode()}, {@link #equals(Object)} e {@link #toString()}
 * são sobrescritos.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * @param <ID> tipo da chave primária. Por exemplo, Integer, Long, etc.
 * @see javax.persistence.Version
 */
@MappedSuperclass
public abstract class BaseEntityStatus<ID extends Serializable> extends BaseEntity<ID> implements EntityStatus<ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095905556778831296L;
	/**
	 * <p>
	 * Define a situação da entidade. Campo obrigatório. Caso o valor seja null na
	 * hora da validação JPA, uma mensagem de exceção de validação será lançada.
	 * </p>
	 * 
	 * @see {@link StatusType}
	 */
	@NotNull(message = CoreUtilsValidationMessages.INFORME_A_SITUACAO)
	@Column(length = 1, nullable = false)
	@Convert(converter = StatusConverter.class)
	protected StatusType situacao = StatusType.ATIVO;

	@Override
	public StatusType getSituacao() {
		return situacao;
	}

	@Override
	public void setSituacao(StatusType sit) {
		this.situacao = sit;
	}

}
