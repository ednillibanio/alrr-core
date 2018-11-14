/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.web.converters;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.leg.rr.al.core.jpa.BaseEntityStatus;

/**
 * A idéia principal é utilizar o indice da lista de items como chave para cada
 * entidade, assim o que submetemos no formulário é o index da lista, e não um
 * valor (Id?) da entidade como de costume. Este converter diferentemente do
 * EntityConverter não altera o estado do componente, evitando assim a
 * utilização de mais memória, ele busca as entidades -que já existem- dentro do
 * componente (SelectOneMenu?) como estado do mesmo.
 * 
 * Assim como os outros converters, este também possui algumas ressalvas. O que
 * podemos citar é
 * 
 * <pre>
 * 1) Por ele se utilizar do index da lista de items talvez não
 * seja possível trabalhar com o componente no lado cliente (javascript) se você
 * depender dos valores (Id?) de cada item.
 * </pre>
 * 
 * @author Ednil Libanio
 * @date 26/07/2012
 * 
 */
@Named
public class IndexConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {

		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {

		if (value != null && !"".equals(value)) {

			// FIXME: Mexi só pra tirar o erro do entity.
			@SuppressWarnings("rawtypes")
			BaseEntityStatus entity = (BaseEntityStatus) value;

			// adiciona item como atributo do componente
			this.addAttribute(component, entity);

			// FIXME: Coloquei cast (Long)
			Long codigo = (Long) entity.getId();
			if (codigo != null) {
				return String.valueOf(codigo);
			}
		}

		return (String) value;
	}

	protected void addAttribute(UIComponent component, BaseEntityStatus<?> o) {
		String key = o.getId().toString(); // codigo da empresa como chave neste
											// caso
		this.getAttributesFrom(component).put(key, o);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}
