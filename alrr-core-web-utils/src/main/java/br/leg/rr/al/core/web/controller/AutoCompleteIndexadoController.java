package br.leg.rr.al.core.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.leg.rr.al.core.dao.DominioIndexadoJPADao;
import br.leg.rr.al.core.domain.StatusType;
import br.leg.rr.al.core.jpa.DominioIndexado;

public abstract class AutoCompleteIndexadoController<T extends DominioIndexado> {

	private DominioIndexadoJPADao<T> bean;

	public AutoCompleteIndexadoController() {

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
			return bean.buscarAtivosPorNome(nome);
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

	/**
	 * Método que busca as entidades que a situação seja 'ativa'
	 * (entity.situacao=StatusType.ATIVO) pelo nome indexado usando
	 * hibernate-search-annotation.
	 * 
	 * @param nome valor a ser pesquisado.
	 * @return lista de entidades encontradas ou null caso contrário.
	 */
	public List<T> completarAtivoPorNomeIndexado(String nome) {
		if (StringUtils.isNotBlank(nome)) {

			return bean.buscarAtivosPorNomeIndexado(nome);

		}

		return null;
	}

	/**
	 * Método que busca as entidades por nome indexado usando
	 * hibernate-search-annotation.
	 * 
	 * @param nome valor a ser pesquisado na lista de nomes indexados.
	 * @return lista de entidades encontradas ou null caso contrário.
	 */
	public List<T> completarPorNomeIndexado(String nome) {
		if (StringUtils.isNotBlank(nome)) {

			return bean.buscarPorNomeIndexado(nome);

		}

		return null;
	}

	public void setBean(DominioIndexadoJPADao<T> bean) {
		this.bean = bean;
	}
}
