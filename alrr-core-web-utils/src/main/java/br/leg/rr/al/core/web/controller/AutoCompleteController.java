package br.leg.rr.al.core.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.leg.rr.al.core.dao.DominioJPADao;
import br.leg.rr.al.core.domain.StatusType;
import br.leg.rr.al.core.jpa.Dominio;

public abstract class AutoCompleteController<T extends Dominio> {

	private DominioJPADao<T> bean;

	public AutoCompleteController() {

	}

	public abstract void init();

	/**
	 * Método que busca no banco de dados, as entidades que a situação seja 'ativa'
	 * (entity.situacao=StatusType.ATIVO) pelo nome informado.
	 * 
	 * @see StatusType
	 * @param nome atributo nome da entidade
	 * @return lista de entidades encontradas ou null caso contrário.
	 */
	public List<T> completarAtivoPorNome(String nome) {
		if (StringUtils.isNotBlank(nome)) {
			return getBean().buscarAtivosPorNome(nome);
		}

		return null;

	}

	/**
	 * Método que busca no banco de dados pelo nome informado.
	 * 
	 * @param nome atributo nome da entidade
	 * @return lista de entidades encontradas ou null caso contrário.
	 */
	public List<T> completarPorNome(String nome) {
		if (StringUtils.isNotBlank(nome)) {
			return bean.buscarPorNome(nome);
		}

		return null;

	}

	public DominioJPADao<T> getBean() {
		return bean;
	}

	public void setBean(DominioJPADao<T> bean) {
		this.bean = bean;
	}

}
