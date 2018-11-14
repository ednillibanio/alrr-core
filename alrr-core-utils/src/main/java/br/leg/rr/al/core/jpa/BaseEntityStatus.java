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
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * @param <ID>
 */
@MappedSuperclass
public abstract class BaseEntityStatus<ID extends Serializable> extends BaseEntity<ID> implements EntityStatus<ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095905556778831296L;
	/**
	 * 
	 */
	@NotNull(message = CoreUtilsValidationMessages.INFORME_A_SITUACAO)
	@Column(length = 1, nullable = false)
	@Convert(converter = StatusConverter.class)
	protected StatusType situacao = StatusType.ATIVO;

	public StatusType getSituacao() {
		return situacao;
	}

	public void setSituacao(StatusType sit) {
		this.situacao = sit;
	}

}
