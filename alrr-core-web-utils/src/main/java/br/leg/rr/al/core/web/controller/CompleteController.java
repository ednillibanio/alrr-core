package br.leg.rr.al.core.web.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.leg.rr.al.core.dao.BeanException;
import br.leg.rr.al.core.dao.JPADao;
import br.leg.rr.al.core.jpa.Entity;
import br.leg.rr.al.core.web.util.FacesMessageUtils;

public abstract class CompleteController<T extends Entity<ID>, ID extends Serializable> {

	private JPADao<T, ID> bean;

	public CompleteController() {

	}

	/**
	 * Definir o bean que irá buscar as entidades por nome;
	 */
	public abstract void init();

	/**
	 * Método usado para buscar Localidades. A busca é realizada por parte do nome
	 * informado.
	 * 
	 * @param nome
	 * @return lista de localidades. <code>null </code> se nenhum encontrado.
	 */
	public List<T> completeByNome(String nome) {
		if (StringUtils.isNotBlank(nome) && (!nome.equals(" - "))) {
			try {
				return getBean().buscarPorNome(nome);
			} catch (BeanException e) {
				FacesMessageUtils.addFatal(e.getMessage());
				e.printStackTrace();
			}

		}

		return null;
	}

	public JPADao<T, ID> getBean() {
		return bean;
	}

	public void setBean(JPADao<T, ID> bean) {
		this.bean = bean;
	}
}
