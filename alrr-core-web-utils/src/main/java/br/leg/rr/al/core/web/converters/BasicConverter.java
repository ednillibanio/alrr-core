/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.web.converters;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.convert.Converter;

import br.leg.rr.al.core.jpa.BaseEntityStatus;

/**
 * 
 * @author libanioe
 * 
 */
public abstract class BasicConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6384032433625770144L;

	/**
	 * Colocar a entity no contexto do componente. Só permite trabalhar com um
	 * objeto.
	 * 
	 * @param component
	 * @param o
	 *            BaseEntityStatus que será armazenado no componente.
	 */
	protected void addAttribute(UIComponent component, BaseEntityStatus<?> o) {
		this.getAttributesFrom(component).put("0", o);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}
