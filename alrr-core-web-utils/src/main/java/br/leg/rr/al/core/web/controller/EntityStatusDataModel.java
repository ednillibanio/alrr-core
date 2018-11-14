package br.leg.rr.al.core.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.leg.rr.al.core.jpa.EntityStatus;

/**
 * @author Ednil Libanio
 * @date 15/07/2012
 * 
 */
public class EntityStatusDataModel<T extends EntityStatus<ID>, ID extends Serializable> extends ListDataModel<T>
		implements SelectableDataModel<EntityStatus<ID>>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8477436959691032346L;

	private int index;

	public EntityStatusDataModel() {
		super();
	}

	public EntityStatusDataModel(List<T> data) {
		super(data);
	}

	@Override
	public EntityStatus<ID> getRowData(String entidadeId) {

		@SuppressWarnings("unchecked")
		List<T> entities = ((List<T>) getWrappedData());

		for (EntityStatus<ID> entity : entities) {
			if (entity.getId().toString().equals(entidadeId)) {
				index = entities.indexOf(entity);
				return entity;
			}
		}

		return null;
	}

	@Override
	public Object getRowKey(EntityStatus<ID> entidade) {
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
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

}
