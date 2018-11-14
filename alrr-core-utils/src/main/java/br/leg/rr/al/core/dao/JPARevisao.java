package br.leg.rr.al.core.dao;

import java.io.Serializable;
import java.util.List;

import br.leg.rr.al.core.jpa.EntityStatus;

public interface JPARevisao<T extends EntityStatus<ID>, ID extends Serializable> {

	public List<T> getRevisoes(ID id);

}
