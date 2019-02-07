package br.leg.rr.al.core.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * @param <ID>
 */
//TODO: falta javadoc
@MappedSuperclass
public abstract class BaseEntityNoSQL<ID extends Serializable> implements Entity<ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8148267451241755568L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		if (getId() != null) {
			result = prime * result + (getId().hashCode() ^ (getId().hashCode() >>> 32));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!getClass().isAssignableFrom(obj.getClass()))
			return false;

		BaseEntityNoSQL<ID> other = (BaseEntityNoSQL<ID>) obj;

		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}
}
