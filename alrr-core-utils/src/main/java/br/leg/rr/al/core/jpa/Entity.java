package br.leg.rr.al.core.jpa;

import java.io.Serializable;

/**
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * @param <ID>
 */
public interface Entity<ID extends Serializable> extends Serializable {

	public ID getId();

	public void setId(ID id);

}
