package br.leg.rr.al.core.jpa;

import java.io.Serializable;

import br.leg.rr.al.core.domain.StatusType;

/**
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * @param <ID>
 */
public interface EntityStatus<ID extends Serializable> extends Entity<ID> {

	public StatusType getSituacao();

	public void setSituacao(StatusType sit);

}
