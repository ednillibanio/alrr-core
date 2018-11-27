package br.leg.rr.al.core.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.leg.rr.al.core.jpa.Entity;

/**
 * @author Ednil Libanio
 * @date 15/07/2012
 * 
 */
public class EntityDataModel<T extends Entity<ID>, ID extends Serializable> extends ListDataModel<T>
		implements SelectableDataModel<Entity<ID>>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7120373657959697907L;

	private int index;

	public EntityDataModel() {
		super();
	}

	public EntityDataModel(List<T> data) {
		super(data);
	}

	@Override
	public Entity<ID> getRowData(String entidadeId) {

		@SuppressWarnings("unchecked")
		List<T> entities = ((List<T>) getWrappedData());

		for (Entity<ID> entity : entities) {
			if (entity.getId().toString().equals(entidadeId)) {
				index = entities.indexOf(entity);
				return entity;
			}
		}

		return null;
	}

	@Override
	public Object getRowKey(Entity<ID> entidade) {
		if (entidade != null) {
			return entidade.getId();
		}
		return null;
	}

	public boolean remove(T entity) {

		@SuppressWarnings("unchecked")
		List<T> entities = ((List<T>) getWrappedData());
		return entities.remove(entity);
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

}
