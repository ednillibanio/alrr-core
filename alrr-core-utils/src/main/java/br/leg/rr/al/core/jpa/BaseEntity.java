package br.leg.rr.al.core.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * @param <ID>
 */
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Entity<ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8264266837455021L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	protected ID id;

	@Version
	@Column(columnDefinition = "integer DEFAULT 0")
	protected Integer version;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

	/**
	 * <p>
	 * Método verifica se duas entidades são a mesma instância ou se possuem os
	 * mesmos valores. Para verificar se são identicas baseadas nos valores, o campo
	 * id e version são validadas.
	 * </p>
	 * <p>
	 * Esse método pode ser usado, por exemplo, para verificar se um registro já não
	 * foi atualizado numa transação anterior. Exemplo:
	 * </p>
	 * 
	 * <pre>
	 * T entidade2 = bean.buscarPorId(entidade1.getId());
	 * if (entidade1.equals(entidade2)){
	 * 	entidade1.setNome('meu teste');
	 * 	bean.salvar(entidade1);
	 * }
	 * </pre>
	 * 
	 * <p>
	 * Veja que a entidade2 foi usada apenas para verificar se a entidade não foi
	 * alterada por outra transação.
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!getClass().isAssignableFrom(obj.getClass())) {
			return false;
		}

		BaseEntity<ID> other = (BaseEntity<ID>) obj;

		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		} else if ((getId().equals(other.getId())) && (getVersion() != other.getVersion())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		// return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id",
		// getId()).toString();
	}

}
