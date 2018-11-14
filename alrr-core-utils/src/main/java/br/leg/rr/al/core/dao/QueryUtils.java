/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
/**
 * QueryUtils.java
 * 16/07/2012
 * 
 * Copyright (c) 2012, KMDR. All rights reserved.
 * Boa Vista, RR - Brasil
 * Este software é propriedade da KMDR e não é permitida a
 * distribuição/alteração da mesma sem prévia autorização.
 *
 */
package br.leg.rr.al.core.dao;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

import org.apache.commons.beanutils.PropertyUtils;

import br.leg.rr.al.core.jpa.EntityStatus;

/**
 * @author Ednil Libanio
 * @date 16/07/2012
 * 
 */
public final class QueryUtils {

	/**
	 * Seta os parametros nomeados carregados em um Map na query.
	 * 
	 * @author <a href="mailto:carlos.manoel@pdcase.com.br">Carlos Manoel</a>.
	 * @param query
	 * @param parametros
	 * @return
	 */
	public static void setQueryParameters(Query query, Map<String, Object> params) {

		for (Entry<String, Object> item : params.entrySet()) {
			query.setParameter(item.getKey(), item.getValue());
		}

	}

	/**
	 * 
	 * @param query
	 * @param parameters
	 * @throws Exception
	 */
	public static void setQueryParameters(Query query, EntityStatus<?> parameters) throws Exception {
		String[] namedParams = (String[]) query.getParameters().toArray();
		for (String param : namedParams) {
			Object value = PropertyUtils.getProperty(parameters, param);
			query.setParameter(param, value);
		}

	}
}
