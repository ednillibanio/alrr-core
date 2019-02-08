package br.leg.rr.al.core.dao;

import java.io.Serializable;

import br.leg.rr.al.core.jpa.Entity;

/**
 * Além desse classe ser do tipo {@code Dao}, ela possui métodos que são usados
 * pela pelos frameworks JPA e EJB.
 * 
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * @since 1.0.0
 * 
 * @param <T> Entidade que será manipulada.
 * @param <ID> Tipo da chave primária ou identificador único.
 */
public interface JPADao<T extends Entity<ID>, ID extends Serializable> extends JPADaoUtils<T, ID>, Dao<T, ID> {

}
